package com.techmaster.login.service;

import com.techmaster.login.dto.UserDTO;
import com.techmaster.login.model.User;
import com.techmaster.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDTO findByUsernameAndPassword(String username,String password) {
        Optional<User> findResult = userRepository.findByUsernameAndPassword(username,password);
        UserDTO userDTO = new UserDTO();
        if(findResult.isPresent()){
            userDTO.setUsername(findResult.get().getUsername());
            userDTO.setEmail(findResult.get().getEmail());
            userDTO.setAvatar(findResult.get().getAvatar());
        }else {
            userDTO = null;
        }
        return userDTO;
    }
}
