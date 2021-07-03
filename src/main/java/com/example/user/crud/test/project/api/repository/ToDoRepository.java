package com.example.user.crud.test.project.api.repository;

import com.example.user.crud.test.project.api.model.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends MongoRepository<ToDo, String> {
}
