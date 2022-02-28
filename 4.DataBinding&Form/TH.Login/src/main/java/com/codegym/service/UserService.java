package com.codegym.service;

import com.codegym.model.Login;
import com.codegym.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static List<User> users;

    static {
        users = new ArrayList<>();
        users.add(new User("john","123456", "John", "john@gmail.com", 26));
        users.add(new User("bill","123456", "Bill", "bill@gmail.com", 18));
        users.add(new User("mike","123456", "Mike", "mike@gmail.com", 12));
    }

    public User checkLogin(Login login) {
        for (User u :
                users) {
            if (u.getAccount().equals(login.getAccount()) && u.getPassword().equals(login.getPassword())) {
                return u;
            }
        }
        return null;
    }
}
