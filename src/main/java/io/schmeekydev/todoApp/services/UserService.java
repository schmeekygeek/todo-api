package io.schmeekydev.todoApp.services;

import java.util.List;

import io.schmeekydev.todoApp.entities.User;

public interface UserService {
    User createUser(User user);
    User updateUser(User user, int userId);
    User getUserById(int userId);
    List<User> getAllUsers();
    void deleteUser(Integer userId);
}
