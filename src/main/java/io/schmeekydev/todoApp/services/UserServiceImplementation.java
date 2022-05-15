package io.schmeekydev.todoApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.schmeekydev.todoApp.entities.User;
import io.schmeekydev.todoApp.exceptions.ResourceNotFoundException;
import io.schmeekydev.todoApp.repositories.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public User updateUser(User user, int userID) {
		User updatedUser =
			this.userRepository.findById(userID)
				.orElseThrow(() -> new ResourceNotFoundException("User", "ID", userID)
				);
		updatedUser.setName(user.getName());
		updatedUser.setEmail(user.getEmail());
		updatedUser.setPassword(user.getPassword());
		updatedUser.setAbout(user.getAbout());
		return this.userRepository.save(updatedUser);
	}

	@Override
	public User getUserById(int userID) {
        User user = this.userRepository.findById(userID)
            .orElseThrow(() -> new ResourceNotFoundException("User", "ID", userID));
        return user;
	}

	@Override
	public List<User> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        return users;
	}

	@Override
	public void deleteUser(Integer userID) {
        User user = this.userRepository.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User", " ID ", userID));
        this.userRepository.delete(user);
	}
}
