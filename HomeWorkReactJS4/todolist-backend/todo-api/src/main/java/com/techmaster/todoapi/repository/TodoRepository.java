package com.techmaster.todoapi.repository;

import com.techmaster.todoapi.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Integer> {
}
