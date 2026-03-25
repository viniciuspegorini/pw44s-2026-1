package br.edu.utfpr.pb.pw44s.server;

import br.edu.utfpr.pb.pw44s.server.controller.UserController;
import br.edu.utfpr.pb.pw44s.server.model.User;
import br.edu.utfpr.pb.pw44s.server.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment =
        SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
@AutoConfigureTestRestTemplate
public class UserControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    
    @Autowired
    private RestTestClient restTestClient;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void postUser_whenUserIsValid_receiveCREATED() {
        User user = createValidUser();

        restTestClient
                    .post()
                    .uri("/users")
                    .body(user)
                    .exchange()
                    .expectStatus().isCreated()
                    .expectBody();
    }

    @Test
    public void postUser_whenUserIsValid_userSavedToDatabase() {
        User user = createValidUser();

        testRestTemplate.postForEntity("/users", user, Object.class);

        assertThat(userRepository.count()).isEqualTo(1);
    }

    @Test
    public void postUser_whenUserIsValid_passwordIsHashedInDatabase() {
        User user = createValidUser();

        testRestTemplate.postForEntity("/users", user, Object.class);
        
        List<User> users = userRepository.findAll();
        User userDB = users.get(0);
        
        assertThat(userDB.getPassword()).isNotEqualTo(user.getPassword());
    }
    
    @Test
    public void postUser_whenUserHasNullUsername_receiveBAD_REQUEST() {
        User user = createValidUser();
        user.setUsername(null);
        
        ResponseEntity<Object> response = 
                testRestTemplate.postForEntity("/users", user, Object.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    private User createValidUser() {
        return User.builder()
                .username("test-user")
                .displayName("test-Display")
                .password("P4ssword").build();
    }
}
