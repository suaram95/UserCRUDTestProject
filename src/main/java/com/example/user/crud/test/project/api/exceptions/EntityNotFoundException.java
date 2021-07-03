package com.example.user.crud.test.project.api.exceptions;

import javax.management.relation.RelationNotFoundException;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
