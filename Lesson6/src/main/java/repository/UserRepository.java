package repository;

import data.User;

import java.sql.SQLException;
import java.util.List;


public interface UserRepository {

    User createUser(User user) throws SQLException;
    User updateUser(User user) throws SQLException;
    void deleteUser(String id) throws SQLException;
    User getUserById(String id);
    List<User> getUsers(String first_name, String last_name, String mid_name);
}
