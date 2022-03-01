package repository;

import data.User;
import java.util.List;

public interface UserRepository {
    List<User> getAll();
    User getBy(String id);
    User save(User user);
    List<User> saveAll(List <User> users);
}
