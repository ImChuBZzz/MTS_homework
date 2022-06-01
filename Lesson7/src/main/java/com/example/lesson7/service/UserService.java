package com.example.lesson7.service;

import com.example.lesson7.data.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User findUserById(String id);
}
