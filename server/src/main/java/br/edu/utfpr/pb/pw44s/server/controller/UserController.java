package br.edu.utfpr.pb.pw44s.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("users")
public class UserController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create() {
        log.info("UserControllerTest create");
    }
}
