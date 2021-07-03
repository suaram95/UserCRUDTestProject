package com.example.user.crud.test.project.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "todos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToDo {

    @Id
    private String todoId;
    private String title;
    private ToDoStatus status;
    private int userId;
}
