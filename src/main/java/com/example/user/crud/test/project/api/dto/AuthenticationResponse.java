package com.example.user.crud.test.project.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AuthenticationResponse {

    private String token;
    private String firstName;
    private String lastName;

}
