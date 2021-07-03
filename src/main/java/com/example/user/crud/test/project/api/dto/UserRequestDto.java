package com.example.user.crud.test.project.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {

    @NotNull(message = "First Name field was required")
    private String firstName;

    @NotNull(message = "Last Name field was required")
    private String lastName;

    @Email(message = "Not valid Email")
    private String email;

    @Size(min = 6, message = "Password may contain min 6 symbols")
    private String password;

    @NotNull(message = "Phone number was required")
    private String phoneNumber;
}
