package com.api.rc_paradise_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.rc_paradise_api.model.User;
import com.api.rc_paradise_api.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class UserController {
	
	private final UserService service;

	// Dependency Injection using constructor
	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}

	// retrive all users
	@GetMapping("/user")
	public ResponseEntity<?> getAllUsers() {
		return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
	}
	
	// insert new user to the system
	@PostMapping("/register/user")
	public ResponseEntity<?> register(@RequestBody User user) {
		return new ResponseEntity<>(service.registerUser(user), HttpStatus.CREATED);
	}

	// update en existing user record
	@PutMapping("/update/user")
	public ResponseEntity<?> update(@RequestBody User user) {
		return new ResponseEntity<>(service.updateUser(user), HttpStatus.OK);
	}

	// delete user by id
	@DeleteMapping("/delete/user/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		return new ResponseEntity<>(service.deleteUser(id), HttpStatus.OK);
	}

	// returning user object after a successful login
	// user authentication and authorization handled by the framework (Spring Security)
	@GetMapping("/login/{phone}")
	public User login(@PathVariable String phone) {
		return service.login(phone);
	}
}
