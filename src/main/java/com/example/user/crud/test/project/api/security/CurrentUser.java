package com.example.user.crud.test.project.api.security;

import com.example.user.crud.test.project.api.model.User;
import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().name()));
    }

    public User getUser() {
        return user;
    }
}
