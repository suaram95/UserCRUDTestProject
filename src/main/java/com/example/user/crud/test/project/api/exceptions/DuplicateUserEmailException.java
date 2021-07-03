package com.example.user.crud.test.project.api.exceptions;


public class DuplicateUserEmailException extends RuntimeException {

    public DuplicateUserEmailException(String msg) {
        super(msg);
    }
}
