package com.example.user.crud.test.project.api.exceptions.handler;

import com.example.user.crud.test.project.api.dto.APIErrorResponse;
import com.example.user.crud.test.project.api.exceptions.AuthenticationFailureException;
import com.example.user.crud.test.project.api.exceptions.DuplicateUserEmailException;
import com.example.user.crud.test.project.api.exceptions.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(
            {DuplicateUserEmailException.class,
                    EntityNotFoundException.class,
                    UsernameNotFoundException.class,
                    AuthenticationFailureException.class}
    )
    public ResponseEntity<?> offerNotValidHandler(Exception exc, ServletWebRequest request) {

        APIErrorResponse apiErrorResponse = APIErrorResponse.builder()
                .timeStamp(LocalDateTime.now())
                .path(request.getDescription(true))
                .status(HttpStatus.BAD_REQUEST)
                .errors(Arrays.asList(exc.getMessage()))
                .build();
        return new ResponseEntity<>(apiErrorResponse, new HttpHeaders(), apiErrorResponse.getStatus());
    }
}
