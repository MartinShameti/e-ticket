package org.internship.service;

import org.internship.entity.User;
import org.internship.repository.UserRepository;
import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        userRepository.save(user);
        System.out.println("User created: " + user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}