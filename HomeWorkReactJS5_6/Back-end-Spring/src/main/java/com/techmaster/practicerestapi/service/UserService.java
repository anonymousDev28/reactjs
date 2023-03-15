package com.techmaster.practicerestapi.service;

import com.techmaster.practicerestapi.dto.PasswordDTO;
import com.techmaster.practicerestapi.dto.UserDTO;
import com.techmaster.practicerestapi.model.User;

import java.util.List;

public interface UserService {
    List<UserDTO> getUsers();
    UserDTO getUserByName(String name);

    UserDTO save(UserDTO userDTO);

    UserDTO getUserById(int id);

    UserDTO put(UserDTO userDTO);

    String delete(int id);

    UserDTO updateAvatar(UserDTO userDTO);

    String updatePassword(int id,PasswordDTO passwordDTO);

    String forgotPassword(int id);
}
