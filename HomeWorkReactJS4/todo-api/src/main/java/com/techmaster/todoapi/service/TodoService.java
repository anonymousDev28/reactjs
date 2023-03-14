package com.example.demoapp.service;

import com.example.demoapp.exception.BadRequestException;
import com.example.demoapp.exception.NotFoundException;
import com.example.demoapp.model.Todo;
import com.example.demoapp.request.CreateTodoRequest;
import com.example.demoapp.request.UpdateTodoRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class TodoService {
    private List<Todo> todos;

    public TodoService() {
        todos =  new ArrayList<>();
//        todos.add(new Todo(1, "Làm bài tập", true, LocalDateTime.now(), "normal"));
//        todos.add(new Todo(2, "Đá bóng", true, LocalDateTime.now(), "medium"));
//        todos.add(new Todo(3, "Đi chơi", false, LocalDateTime.now(), "high"));
        todos.add(new Todo(1, "Làm bài tập", true));
        todos.add(new Todo(2, "Đá bóng", true));
        todos.add(new Todo(3, "Đi chơi", false));
    }

    private int generateId() {
        Random rd = new Random();
        return rd.nextInt(1000 - 100 + 1) + 100;
//        return (int) Math.floor(Math.random() + (1000 - 100 + 1) + 100);
    }

    public List<Todo> getAllTodo() {
        return todos;
    }

    // error => object : nguyên nhân lỗi
    // message, path, time, ...
    // status : mã lỗi
    public Todo getTodoById(Integer id) {
        for (Todo t: todos) {
            if(Objects.equals(t.getId(), id)) {
                return t;
            }
        }
        throw new NotFoundException("Not found todo with id = " + id);
    }

    public Todo createTodo(CreateTodoRequest request) {
        Todo todo = Todo.builder()
                .id(generateId())
                .title(request.getTitle())
                .status(false)
//                .createdAt(LocalDateTime.now())
//                .level(request.getLevel())
                .build();

        todos.add(todo);
        return todo;
    }

    public Todo updateTodo(Integer id, UpdateTodoRequest request) {
        for (Todo t: todos) {
            if(Objects.equals(t.getId(), id)) {
                t.setTitle(request.getTitle());
                t.setStatus(request.getStatus());
//                t.setLevel(request.getLevel());

                return t;
            }
        }
        return null;
    }

    public void deleteTodo(Integer id) {
        todos.removeIf(todo -> Objects.equals(todo.getId(), id));
    }
}
