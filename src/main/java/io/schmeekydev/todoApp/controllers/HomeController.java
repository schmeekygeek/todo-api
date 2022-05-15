package io.schmeekydev.todoApp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.schmeekydev.todoApp.entities.User;
import io.schmeekydev.todoApp.payloads.AuthenticationRequest;
import io.schmeekydev.todoApp.payloads.JWTResponse;
import io.schmeekydev.todoApp.security.JWTUtils;
import io.schmeekydev.todoApp.security.MyUserDetailsService;
import io.schmeekydev.todoApp.services.UserService;

@RestController
@RequestMapping("/api/v1")
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTUtils jwtUtils;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    // endpoint to register
    @PostMapping("/createAccount")
    public ResponseEntity<User> createAccount(@Valid @RequestBody User user){
		User createdUser = this.userService.createUser(user);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // endpoint to sign in
    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        final UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        JWTResponse jwt = new JWTResponse(true, jwtUtils.generateToken(userDetails));
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }
}
