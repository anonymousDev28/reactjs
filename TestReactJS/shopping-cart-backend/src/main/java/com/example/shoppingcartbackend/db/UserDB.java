package com.example.shoppingcartbackend.db;

import com.example.shoppingcartbackend.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDB {
    public static List<User> users = new ArrayList<>(List.of(
            new User(1,"Nguyen Van Quang","quangmanh28496@gmail.com","012434780","avatar001"),
            new User(2,"Nguyen Van Manh","nguyenvanb@gmail.com","01346789","avatar002"),
            new User(3,"Nguyen Van Chinh","nguyenvanc@gmail.com","01234659","avatar003"),
            new User(4,"Nguyen Van Dung","nguyenvand@gmail.com","01234567565","avatar004")
    ));
}
