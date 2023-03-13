package com.techmaster.login.service;

import com.techmaster.login.dto.UserDTO;

import java.util.Optional;

public interface UserService {
    public UserDTO findByUsernameAndPassword(String username, String password);
}
