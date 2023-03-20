package com.techmaster.todoapi.service;

import com.techmaster.todoapi.dto.TodoDTO;
import com.techmaster.todoapi.exception.NotFoundException;
import com.techmaster.todoapi.model.Todo;
import com.techmaster.todoapi.repository.TodoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class TodoService {
    @Autowired
    TodoRepository todoRepository;

    private int generateId() {
        Random rd = new Random();
        return rd.nextInt(1000 - 100 + 1) + 100;
    }

    public List<TodoDTO> getAllTodo() {
        List<Todo> todos = todoRepository.findAll();
        ModelMapper mapper = new ModelMapper();
        List<TodoDTO> todoDTOS = todos.stream().map(todo -> mapper.map(todo, TodoDTO.class)).toList();
        return todoDTOS;
    }

    // error => object : nguyên nhân lỗi
    // message, path, time, ...
    // status : mã lỗi
    public TodoDTO getTodoById(Integer id) {
        List<Todo> todos = todoRepository.findAll();
        for (Todo t: todos) {
            if(Objects.equals(t.getId(), id)) {

                return new ModelMapper().map(t, TodoDTO.class);
            }
        }
        throw new NotFoundException("Not found todo with id = " + id);
    }

    public TodoDTO updateTodo(TodoDTO todoDTO) {
        List<Todo> todos = todoRepository.findAll();
        for (Todo t: todos) {
            if(Objects.equals(t.getId(), todoDTO.getId())) {
                t.setTitle(todoDTO.getTitle());
                t.setStatus(todoDTO.getStatus());
                return new ModelMapper().map(t, TodoDTO.class);
            }
        }
        return null;
    }

    public void deleteTodo(Integer id) {
        List<Todo> todos = todoRepository.findAll();
        todos.removeIf(todo -> Objects.equals(todo.getId(), id));
    }

    public TodoDTO createTodo(TodoDTO todoDTO) {
        List<Todo> todos = todoRepository.findAll();
        Todo todo = Todo.builder()
                .id(generateId())
                .title(todoDTO.getTitle())
                .status(false)
                .build();
        todos.add(todo);
        return new ModelMapper().map(todo, TodoDTO.class);
    }
}
