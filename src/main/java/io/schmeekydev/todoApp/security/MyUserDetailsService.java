package io.schmeekydev.todoApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import io.schmeekydev.todoApp.entities.User;
import io.schmeekydev.todoApp.exceptions.ResourceNotFoundException;
import io.schmeekydev.todoApp.repositories.UserRepository;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        return user;
	}
}
