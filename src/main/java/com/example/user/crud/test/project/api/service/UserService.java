package com.example.user.crud.test.project.api.service;

import com.example.user.crud.test.project.api.dto.AuthenticationRequest;
import com.example.user.crud.test.project.api.dto.UserRequestDto;
import com.example.user.crud.test.project.api.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<User> registerUser(UserRequestDto userRequestDto);

    ResponseEntity<?> authenticateUser(AuthenticationRequest authenticationRequest);

    User getUserById(String userId);

    User updateUser(User user);

    void removeUser(String userId);
}
