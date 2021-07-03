package com.example.user.crud.test.project.api.controller;

import com.example.user.crud.test.project.api.dto.ToDoRequestDto;
import com.example.user.crud.test.project.api.model.ToDo;
import com.example.user.crud.test.project.api.service.ToDoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
@Slf4j
public class ToDoController {

    private final ToDoService toDoService;

    @PostMapping("/createTodo")
    public ToDo createTodo(@RequestBody ToDoRequestDto toDoRequestDto) {
        log.info("Calling Todo Service todo creation method");
        return toDoService.createTodo(toDoRequestDto);
    }

    @GetMapping("/getTodoList")
    public List<ToDo> getTodoList(){
        log.info("Calling Todo Service getTodoList method");
        return toDoService.getTodoList();
    }

    @PutMapping("/updateTodo")
    public ToDo updateToDo(@RequestBody ToDo toDo){
        log.info("Calling Todo Service update method");
        return toDoService.updateToDo(toDo);
    }

    @DeleteMapping("/removeTodo/{id}")
    public ResponseEntity<?> removeTodo(@PathVariable(name = "id") String todoId){
        log.info("Calling Todo Service remove method");
        toDoService.removeTodo(todoId);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully Removed");
    }
}
