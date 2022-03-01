package services.impl;

import data.User;
import data.UserStatus;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) { this.userRepository = userRepository; }
    public UserServiceImpl() { this.userRepository = new UserRepositoryImpl(); }

    @Override
    public List<User> getUsers(String s) {
        String[] str = s.split(" ");
        List<User> goodUsers = new ArrayList<>();

        switch (str.length) {
            case 3:
                for(User u: this.userRepository.getAll()) {
                    if(u.getSurname().equals(str[0])
                            && u.getName().equals(str[1])
                            && u.getMidName().equals(str[2])
                            && u.getUserStatus() == UserStatus.ACTIVE) {
                        goodUsers.add(u);
                    }
                }
                break;
            case 1:
                if (str[0].chars().allMatch(Character::isDigit)) {
                    for(User u: this.userRepository.getAll()) {
                        if(u.getPhone().equals(str[0]) && u.getUserStatus() == UserStatus.ACTIVE) {
                            goodUsers.add(u);
                        }
                    }
                }
                else {
                    for(User u: this.userRepository.getAll()) {
                        if(u.getEmail().equals(str[0]) && u.getUserStatus() == UserStatus.ACTIVE) {
                            goodUsers.add(u);
                        }
                    }
                }
                break;
            default:
                break;
        }
        return goodUsers;
    }

    @Override
    public void deleteUsers(List<String> ids) {
        User user;
        for(String s: ids) {
            user = userRepository.getBy(s);
            user.setUserStatus(UserStatus.DELETED);
            this.userRepository.save(user);
        }
    }

    @Override
    public User updateUser(String id, String surname, String name, String midName, String phone, String email) {
        User user = this.userRepository.getBy(id);
        if(user.getUserStatus() == UserStatus.DELETED) {
            System.out.printf("Can't update user with id %s , because it's status is DELETED", id);
        }
        else {
            user.setSurname(surname);
            user.setName(name);
            user.setMidName(midName);
            user.setPhone(phone);
            user.setEmail(email);
        }
        return user;
    }

    @Override
    public User createUser(String surname, String name, String midName, String phone, String email) {
        User user = new User(UUID.randomUUID().toString(), surname, name, midName, phone, email);

        this.userRepository.save(user);
        return user;
    }
}
