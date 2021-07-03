package com.example.user.crud.test.project.api.service;

import com.example.user.crud.test.project.api.dto.ToDoRequestDto;
import com.example.user.crud.test.project.api.exceptions.EntityNotFoundException;
import com.example.user.crud.test.project.api.model.ToDo;
import com.example.user.crud.test.project.api.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.user.crud.test.project.api.model.ToDoStatus.NEW;

@Service
@RequiredArgsConstructor
@Slf4j
public class ToDoServiceImpl implements ToDoService {

    private final ToDoRepository toDoRepository;

    @Override
    public ToDo createTodo(ToDoRequestDto toDoRequestDto) {
        log.info("Creating todo...");

        ToDo toDo = ToDo.builder()
                .title(toDoRequestDto.getTitle())
                .status(NEW)
                .userId(toDoRequestDto.getUserId())
                .build();
        log.info("Todo successfully created -{}", toDo);
        return toDoRepository.save(toDo);
    }

    @Override
    public List<ToDo> getTodoList() {
        log.info("Al Todos -{}", toDoRepository.findAll());
        return toDoRepository.findAll();
    }

    @Override
    public ToDo updateToDo(ToDo toDo) {
        log.info("Updating todo...");
        Optional<ToDo> toDoOptional = toDoRepository.findById(toDo.getTodoId());
        log.info("Getting todoOptional -{}", toDoOptional);
        if (toDoOptional.isPresent()) {
            ToDo todoFromDB = toDoOptional.get();
            todoFromDB.setTitle(toDo.getTitle());
            todoFromDB.setStatus(toDo.getStatus());
            todoFromDB.setUserId(toDo.getUserId());
            log.info("Todo details changed and saved to DB, todo- {}", todoFromDB);
            return toDoRepository.save(todoFromDB);
        } else {
            log.error("Todo update failed, todoId:" + toDo.getTodoId() + " was invalid");
            throw new EntityNotFoundException("Todo with id: " + toDo.getTodoId() + " was not found");
        }
    }

    @Override
    public void removeTodo(String todoId) {
        log.info("Removing todo...");
        Optional<ToDo> toDoOptional = toDoRepository.findById(todoId);
        log.info("Getting todoOptional -{}", toDoOptional);
        if (toDoOptional.isPresent()) {
            log.info("Todo: " + toDoOptional.get() + " successfully removed");
            toDoRepository.deleteById(todoId);
        } else {
            throw new EntityNotFoundException("Todo with id: " + todoId + " was not found");
        }
    }
}
