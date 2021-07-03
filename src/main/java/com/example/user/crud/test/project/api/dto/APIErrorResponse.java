package com.example.user.crud.test.project.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class APIErrorResponse {

    private LocalDateTime timeStamp;
    private HttpStatus status;
    private List<String> errors;
    private String path;
}
