package com.techmaster.todoapi.service;

import com.techmaster.todoapi.dto.TodoDTO;
import com.techmaster.todoapi.exception.NotFoundException;
import com.techmaster.todoapi.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class TodoService {
    private List<Todo> todos;

    public TodoService() {
        todos =  new ArrayList<>();
        todos.add(new Todo(0, "Làm bài tập", true));
        todos.add(new Todo(1, "Đá bóng", true));
        todos.add(new Todo(2, "Đi chơi", false));
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

    public Todo updateTodo(TodoDTO todoDTO) {
        for (Todo t: todos) {
            if(Objects.equals(t.getId(), todoDTO.getId())) {
                t.setTitle(todoDTO.getTitle());
                t.setStatus(todoDTO.getStatus());
//                t.setLevel(request.getLevel());
                return t;
            }
        }
        return null;
    }

    public void deleteTodo(Integer id) {
        todos.removeIf(todo -> Objects.equals(todo.getId(), id));
    }

    public Todo createTodo(TodoDTO todoDTO) {
        Todo todo = Todo.builder()
                .id(generateId())
                .title(todoDTO.getTitle())
                .status(false)
//                .createdAt(LocalDateTime.now())
//                .level(request.getLevel())
                .build();
        todos.add(todo);
        return todo;
    }
}
