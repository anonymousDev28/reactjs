package com.techmaster.login.repository;

import com.techmaster.login.fakeDB.UserDB;
import com.techmaster.login.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
    public Optional<User> findByUsernameAndPassword(String username,String password){
        return UserDB.users.stream().filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password)).toList().stream().findFirst();
    }
}
