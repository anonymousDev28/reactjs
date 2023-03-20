package com.techmaster.todoapi.controller;


import com.techmaster.todoapi.dto.TodoDTO;
import com.techmaster.todoapi.model.Todo;
import com.techmaster.todoapi.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// CRUD
@RestController
@RequestMapping("api")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    @GetMapping("todos")
    public List<TodoDTO> getAllTodo() {
        return todoService.getAllTodo();
    }

    @GetMapping("todos/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable Integer id) {
//        try {
//            Todo todo = todoService.getTodoById(id);
//            return ResponseEntity.ok(todo);
//        } catch (NotFoundException e) {
//            ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
//            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//        }
        TodoDTO todoDTO = todoService.getTodoById(id);
        return ResponseEntity.ok(todoDTO);
    }

    @PostMapping("todos")
    @ResponseStatus(HttpStatus.CREATED) // 201
    public TodoDTO createTodo(@Valid @RequestBody TodoDTO todoDTO) {
        return todoService.createTodo(todoDTO);
    }

    @PutMapping("todos")
    public TodoDTO updateTodo(@RequestBody TodoDTO todoDTO) {
        return todoService.updateTodo(todoDTO);
    }

    @DeleteMapping("todos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void deleteTodo(@PathVariable Integer id) {
        todoService.deleteTodo(id);
    }
}
