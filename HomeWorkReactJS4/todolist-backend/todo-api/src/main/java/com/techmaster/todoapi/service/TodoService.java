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
import java.util.Optional;
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
        Optional<Todo> todo= todoRepository.findById(id);
        if(todo.isEmpty()){
            throw new NotFoundException("Not found todo with id = " + id);
        }
        return new ModelMapper().map(todo.get(), TodoDTO.class);
    }

    public TodoDTO updateTodo(TodoDTO todoDTO) {
        Optional<Todo> todo= todoRepository.findById(todoDTO.getId());
        if(todo.isEmpty()){
            throw new NotFoundException("Not found todo with id = " + todoDTO.getId());
        }
        Todo todoDummy = todo.get();
        todoDummy.setTitle(todoDTO.getTitle());
        todoDummy.setStatus(todoDTO.getStatus());
        todoRepository.save(todoDummy);
        return new ModelMapper().map(todo.get(), TodoDTO.class);
    }

    public void deleteTodo(Integer id) {
        Optional<Todo> todo= todoRepository.findById(id);
        if(todo.isEmpty()){
            throw new NotFoundException("Not found todo with id = " + id);
        }
        todoRepository.deleteById(id);
    }

    public TodoDTO createTodo(TodoDTO todoDTO) {
        Todo todo = Todo.builder()
                .title(todoDTO.getTitle())
                .status(false)
                .build();
        todoRepository.save(todo);
        return new ModelMapper().map(todo, TodoDTO.class);
    }
}
