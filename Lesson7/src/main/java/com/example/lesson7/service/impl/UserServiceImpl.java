package com.example.lesson7.service.impl;

import com.example.lesson7.data.User;
import com.example.lesson7.data.exceptions.RequiredFieldMissedException;
import com.example.lesson7.data.exceptions.UserNotFoundException;
import com.example.lesson7.repo.UserRepository;
import com.example.lesson7.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        validation(user);
        user.setId(UUID.randomUUID().toString());
        userRepository.save(user);
        return user;
    }

    @Override
    public User findUserById(String id) {
        return userRepository.findById(id).orElseThrow(()->new UserNotFoundException(String.format("User with %s id not found", id)));
    }


    private void validation(User user) {
        if (user.getFirstName().isBlank()) throw new RequiredFieldMissedException("Field 'name' is empty");
        if (user.getLastName().isBlank()) throw new RequiredFieldMissedException("Field 'last name' is empty");
    }
}
