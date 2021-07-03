package com.example.user.crud.test.project.api.service;

import com.example.user.crud.test.project.api.dto.AuthenticationRequest;
import com.example.user.crud.test.project.api.dto.AuthenticationResponse;
import com.example.user.crud.test.project.api.dto.UserRequestDto;
import com.example.user.crud.test.project.api.exceptions.AuthenticationFailureException;
import com.example.user.crud.test.project.api.exceptions.DuplicateUserEmailException;
import com.example.user.crud.test.project.api.exceptions.EntityNotFoundException;
import com.example.user.crud.test.project.api.model.User;
import com.example.user.crud.test.project.api.repository.UserRepository;
import com.example.user.crud.test.project.api.util.JWTTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JWTTokenUtil jwtTokenUtil;

    @Override
    public ResponseEntity<User> registerUser(UserRequestDto userRequestDto) {
        log.info("Registering user...");

        if (!userRepository.findByEmail(userRequestDto.getEmail()).isPresent()) {
            userRequestDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
            User user = modelMapper.map(userRequestDto, User.class);

            log.info("Registered user -{}", user);
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } else {
            log.error("Duplicate User, email " + userRequestDto.getEmail() + " already exists");
            throw new DuplicateUserEmailException("Email: " + userRequestDto.getEmail() + " was already exists");
        }
    }

    @Override
    public ResponseEntity<?> authenticateUser(AuthenticationRequest authenticationRequest) {
        log.info("User authentication...");
        Optional<User> userByEmail = userRepository.findByEmail(authenticationRequest.getEmail());
        log.info("Getting optional user byEmail: "+authenticationRequest.getEmail());
        if (userByEmail.isPresent()) {
            User user = userByEmail.get();
            log.info("Getting user: "+user);
            if (passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
                String token = jwtTokenUtil.generateToken(user.getEmail());
                log.info("Generated token: "+token);
                log.info("User authentication has success");
                return ResponseEntity.ok(AuthenticationResponse.builder()
                        .token(token)
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .build());
            }
        }
        log.error("User authentication failed, email or password was wrong");
        throw new AuthenticationFailureException("Authentication failed, email or password was wrong");
    }

    @Override
    public User getUserById(String userId) {
        log.info("Getting user...");

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            log.info("User with id: " + userId + " was found");
            return userOptional.get();
        } else {
            log.error("User with id: " + userId + " was not found");
            throw new EntityNotFoundException("User with id:" + userId + " does not exists");
        }
    }

    @Override
    public User updateUser(User user) {
        log.info("Updating user details...");

        Optional<User> userOptional = userRepository.findById(user.getUserId());
        log.info("Getting user from DB with userID: " + user.getUserId());

        if (userOptional.isPresent()) {
            User userFromDB = userOptional.get();
            userFromDB.setFirstName(user.getFirstName());
            userFromDB.setLastName(user.getLastName());
            userFromDB.setEmail(user.getEmail());
            userFromDB.setPassword(user.getPassword());
            userFromDB.setPhoneNumber(user.getPhoneNumber());
            log.info("User: " + userFromDB + " details was updated, and saved to DB");
            return userRepository.save(userFromDB);
        } else {
            log.error("User update failed, userId: " + user.getUserId() + " was not found");
            throw new EntityNotFoundException("User with id: " + user.getUserId() + " was not found");
        }
    }

    @Override
    public void removeUser(String userId) {
        log.info("Removing user...");

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            log.info("User: " + userOptional.get() + " was removed ");
            userRepository.deleteById(userId);
        } else {
            log.error("User remove failed, userid: " + userId + " does not exists");
            throw new EntityNotFoundException("User with id: " + userId + " was not found");
        }
    }
}
