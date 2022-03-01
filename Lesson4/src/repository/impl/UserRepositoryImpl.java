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
        return  this.users;
    }

    @Override
    public User getBy(String id) {
        User ourUser = new User();
        for (User user: this.users) {
            if(user.getId().equals(id)) {
                ourUser = user;
                break;
            }
            else { ourUser = null; }

        }
        return ourUser;
    }

    @Override
    public User save(User user) {
        boolean check = true;
        int index = 0;
        for (User u: this.users) {
            if (user.getId().equals(u.getId())) {
                check = false;
                index = users.indexOf(u);
                break;
            }
        }
        if (check) { this.users.add(user); }
        else { users.set(index, user); }


        return check ? user : users.get(index);
    }

    @Override
    public List<User> saveAll(List<User> users) {
        for(User u: users) { this.save(u); }
        return this.users;
    }

}
