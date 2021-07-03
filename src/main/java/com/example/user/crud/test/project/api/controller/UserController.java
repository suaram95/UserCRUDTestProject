package com.example.user.crud.test.project.api.controller;

import com.example.user.crud.test.project.api.dto.AuthenticationRequest;
import com.example.user.crud.test.project.api.dto.UserRequestDto;
import com.example.user.crud.test.project.api.model.User;
import com.example.user.crud.test.project.api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/registerUser")
    public ResponseEntity<User> registerUser(@RequestBody @Valid UserRequestDto userRequestDto) {
        log.info("Calling User Service registration method");
        return userService.registerUser(userRequestDto);
    }

    @PostMapping("/userAuth")
    public ResponseEntity<?> userAuthentication(@RequestBody AuthenticationRequest authenticationRequest) {
        log.info("Calling User Service user authentication method");
        return userService.authenticateUser(authenticationRequest);
    }

    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable(name = "id") String userId) {
        log.info("Calling User Service getUserById() method");
        return userService.getUserById(userId);
    }

    @PutMapping("/updateUser")
    public User updateUserDetails(@RequestBody User user) {
        log.info("Calling User Service user updating method");
        return userService.updateUser(user);
    }

    @DeleteMapping("/removeUser/{id}")
    public void removeUser(@PathVariable(name = "id") String userId) {
        log.info("Calling User service remove method");
        userService.removeUser(userId);
    }
}
