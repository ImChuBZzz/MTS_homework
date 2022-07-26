package services;

import data.User;

import java.util.List;

public interface UserService {
    List<User> getUsers(String s);
    void deleteUsers(List<String> ids);
    User updateUser(String id, String surname, String name, String midName, String phone, String email);
    User createUser(String surname, String name, String midName, String phone, String email);
}
