package com.example.demoapp.controller;

import com.example.demoapp.exception.ErrorResponse;
import com.example.demoapp.exception.NotFoundException;
import com.example.demoapp.model.Todo;
import com.example.demoapp.request.CreateTodoRequest;
import com.example.demoapp.request.UpdateTodoRequest;
import com.example.demoapp.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    // Inject Bean
    // Field
    // Constructor
    // Setter
    @GetMapping("todos")
    public List<Todo> getAllTodo() {
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
        Todo todo = todoService.getTodoById(id);
        return ResponseEntity.ok(todo);
    }

    @PostMapping("todos")
    @ResponseStatus(HttpStatus.CREATED) // 201
    public Todo createTodo(@Valid @RequestBody CreateTodoRequest request) {
        return todoService.createTodo(request);
    }

    @PutMapping("todos/{id}")
    public Todo updateTodo(@PathVariable Integer id, @RequestBody UpdateTodoRequest request) {
        return todoService.updateTodo(id, request);
    }

    @DeleteMapping("todos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void deleteTodo(@PathVariable Integer id) {
        todoService.deleteTodo(id);
    }
}
