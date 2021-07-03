package com.example.user.crud.test.project.api.service;

import com.example.user.crud.test.project.api.dto.ToDoRequestDto;
import com.example.user.crud.test.project.api.model.ToDo;

import java.util.List;

public interface ToDoService {

    ToDo createTodo(ToDoRequestDto toDoRequestDto);

    List<ToDo> getTodoList();

    ToDo updateToDo(ToDo toDo);

    void removeTodo(String todoId);
}
