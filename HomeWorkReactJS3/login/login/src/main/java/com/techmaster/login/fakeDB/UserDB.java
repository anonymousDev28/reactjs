package com.techmaster.login.fakeDB;

import com.techmaster.login.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDB {
    public static List<User> users =new ArrayList<>(List.of(
            new User(1, "quang1", "test@gmail.com", "123456", "77c0d884-9c49-4b2b-bc7d-3f9aaaab5582"),
            new User(1, "quang2", "test@gmail.com", "123456", "77c0d884-9c49-4b2b-bc7d-3f9aaaab5582")
            ));
}
