package com.example.user.crud.test.project.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class ToDoRequestDto {

    @NotNull(message = "Title field was required")
    private String title;

    @NotNull(message = "User not initialized")
    private int userId;
}
