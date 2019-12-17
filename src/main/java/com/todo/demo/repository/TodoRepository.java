package com.todo.demo.repository;

import com.todo.demo.model.Todos;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoRepository extends MongoRepository<Todos, String> {

}
