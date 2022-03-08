package repository.impl;

import data.User;
import repository.UserRepository;


import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final List<User> users;

    public UserRepositoryImpl(List<User> users) { this.users = users; }
    public UserRepositoryImpl() { this.users = new ArrayList<>(); }

    @Override
    public List<User> getAll() {
        return this.users;
    }

    @Override
    public User getBy(String id) {
        User ourUser = new User();
        for (User user: this.users) {
            if(user.getId().equals(id)) {
                ourUser = user;
                break;
            } // тут без элса норм
        }
        return ourUser;
    }

    @Override
    public User save(User user) {
        int indexOfUser = this.users.indexOf(user); // здесь можно заюзать indexOf с определением equals
        if (indexOfUser != -1) {
            this.users.set(indexOfUser, user);
            return user;
        }
        this.users.add(user);
        return user;
    }

    @Override
    public List<User> saveAll(List<User> users) {
        for(User u: users) { this.save(u); }
        return this.users;
    }
}
