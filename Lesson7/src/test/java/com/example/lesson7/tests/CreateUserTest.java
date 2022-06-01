package com.example.lesson7.tests;

import com.example.lesson7.data.User;
import com.example.lesson7.data.exceptions.RequiredFieldMissedException;
import com.example.lesson7.data.exceptions.UserNotFoundException;
import com.example.lesson7.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CreateUserTest {

    private static final User TEST_USER_1 = new User("Qwe", "Rty", "Asd", "123");


    @Autowired
    UserService userService;

    @Test
    public void TestUserCreation() {
        userService.createUser(TEST_USER_1);
        Assertions.assertEquals(TEST_USER_1, userService.findUserById(TEST_USER_1.getId()));
        System.out.println("passed");
    }

    @Test
    public void TestEmptyField() {
        User user = TEST_USER_1;
        user.setFirstName("");
        Assertions.assertThrows(RequiredFieldMissedException.class, ()->userService.createUser(user));
        System.out.println("passed");
    }

    @Test
    public void TestUserNotFound() {
        User user = TEST_USER_1;
        user.setId("id1");
        Assertions.assertThrows(UserNotFoundException.class, ()->userService.findUserById(user.getId()));
    }
}
