package com.example.user.crud.test.project.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class UserCrudTestProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCrudTestProjectApplication.class, args);
    }
}
