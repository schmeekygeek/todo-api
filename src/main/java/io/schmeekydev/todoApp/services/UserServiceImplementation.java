package io.schmeekydev.todoApp.services;

import io.schmeekydev.todoApp.entities.User;
import io.schmeekydev.todoApp.exceptions.ResourceNotFoundException;
import io.schmeekydev.todoApp.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(User user) {
		User newUser = this.userRepository.save(user);
		return newUser;
	}

	@Override
	public User updateUser(User user, int userId) {
		User updatedUser =
			this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId)
				);
		updatedUser.setName(user.getName());
		updatedUser.setEmail(user.getEmail());
		updatedUser.setPassword(user.getPassword());
		updatedUser.setAbout(user.getAbout());
		return this.userRepository.save(updatedUser);
	}

	@Override
	public User getUserById(int userId) {
        User user = this.userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));
        return user;
	}

	@Override
	public List<User> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        return users;
	}

	@Override
	public void deleteUser(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " ID ", userId));
        this.userRepository.delete(user);
	}
}
