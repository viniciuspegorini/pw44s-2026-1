package br.edu.utfpr.pb.pw44s.server;

import br.edu.utfpr.pb.pw44s.server.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment =
        SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserControllerTest {

    @Test
    public void postUser_whenUserIsValid_receiveCREATED() {
        User user = User.builder()
                .username("test-user")
                .displayName("test-Display")
                .password("P4ssword").build();


    }
}
