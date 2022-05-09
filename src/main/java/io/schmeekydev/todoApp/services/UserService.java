package io.schmeekydev.todoApp.services;

import java.util.List;

import io.schmeekydev.todoApp.entities.User;

public interface UserService {
    User createUser(User user);
    User updateUser(User user, int userID);
    User getUserById(int userID);
    List<User> getAllUsers();
    void deleteUser(Integer userID);
}
