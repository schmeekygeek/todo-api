package io.schmeekydev.todoApp.controllers;

import io.schmeekydev.todoApp.entities.User;
import io.schmeekydev.todoApp.payloads.ApiResponse;
import io.schmeekydev.todoApp.services.UserService;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {

	@Autowired
	private UserService userService;

	private static final String USER_DELETED_MSG =
		"Successfully deleted user with the id %s ";

	// POST
	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User createdUser = this.userService.createUser(user);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	// PUT
	@PutMapping("/update/{userID}")
	public ResponseEntity<User> updateUser(
		@RequestBody User user,
		@PathVariable("userID") int userID
	) {
		return new ResponseEntity<>(
			userService.updateUser(user, userID),
			HttpStatus.OK
		);
	}

	// DELETE
	@DeleteMapping("delete/{id}")
	public ResponseEntity<ApiResponse> deleteUser(
		@PathVariable("id") int userID
	) {
        this.userService.deleteUser(userID);
		return new ResponseEntity<>(
			new ApiResponse(String.format(USER_DELETED_MSG, userID), true),
			HttpStatus.OK
		);
	}

	// GET all Users
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}

	// GET User by ID
	@GetMapping("/{userID}")
	public ResponseEntity<User> getUserById(@PathVariable("userID") int userID) {
		return new ResponseEntity<>(userService.getUserById(userID), HttpStatus.OK);
	}
}
