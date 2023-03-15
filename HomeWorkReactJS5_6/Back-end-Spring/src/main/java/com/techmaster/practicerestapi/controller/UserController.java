package com.techmaster.practicerestapi.controller;

import com.techmaster.practicerestapi.dto.PasswordDTO;
import com.techmaster.practicerestapi.dto.UserDTO;
import com.techmaster.practicerestapi.model.User;
import com.techmaster.practicerestapi.service.UserService;
import com.techmaster.practicerestapi.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @GetMapping
    public List<UserDTO> users(){
        return userService.getUsers();
    }
    @GetMapping("/user")
    public ResponseEntity<?> getUserByName(@RequestParam String name){
        return ResponseEntity.ok(userService.getUserByName(name));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @PostMapping
    public ResponseEntity<?> postUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.save(userDTO));
    }
    @PutMapping
    public ResponseEntity<?> putUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.put(userDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        return ResponseEntity.ok(userService.delete(id));
    }
//    @PutMapping("/update-avatar")
//    public ResponseEntity<?> updateAvatar(@ModelAttribute("file") MultipartFile file, @RequestBody UserDTO userDTO){
//        return ResponseEntity.ok(userService.updateAvatar2(userDTO,file));
//    }
    @PutMapping("{id}/update-avatar")
    public ResponseEntity<?> updateAvatar(@ModelAttribute("file") MultipartFile file, @PathVariable Integer id){
        return ResponseEntity.ok(userService.updateAvatar2(id,file));
    }
    @PutMapping("{id}/update-password")
    public ResponseEntity<?> updatePassword(@PathVariable int id,@RequestBody PasswordDTO passwordDTO){
        return ResponseEntity.ok(userService.updatePassword(id,passwordDTO));
    }
    @PostMapping("{id}/forgot-password")
    public ResponseEntity<?> forgotPassword(@PathVariable int id){
        return ResponseEntity.ok(userService.forgotPassword(id));
    }
}
