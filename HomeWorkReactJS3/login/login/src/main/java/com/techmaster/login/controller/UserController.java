package com.techmaster.login.controller;

import com.techmaster.login.dto.UserDTO;
import com.techmaster.login.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO){
        UserDTO result = userService.findByUsernameAndPassword(userDTO.getUsername(),userDTO.getPassword());
        return result != null ? ResponseEntity.ok(result):ResponseEntity.badRequest().build();
    }
}
