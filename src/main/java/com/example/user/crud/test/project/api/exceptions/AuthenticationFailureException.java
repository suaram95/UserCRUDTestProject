package com.example.user.crud.test.project.api.exceptions;

import javax.naming.AuthenticationException;

public class AuthenticationFailureException extends RuntimeException {

    public AuthenticationFailureException(String explanation) {
        super(explanation);
    }
}
