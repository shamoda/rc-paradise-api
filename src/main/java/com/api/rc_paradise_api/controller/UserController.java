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
	
	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}
	
	@GetMapping("/user")
	public ResponseEntity<?> getAllUsers() {
		return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
	}
	

	@PostMapping("/register/user")
	public ResponseEntity<?> register(@RequestBody User user) {
		return new ResponseEntity<>(service.registerUser(user), HttpStatus.CREATED);
	}
	
	@PutMapping("/update/user")
	public ResponseEntity<?> update(@RequestBody User user) {
		return new ResponseEntity<>(service.updateUser(user), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/user/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		return new ResponseEntity<>(service.deleteUser(id), HttpStatus.OK);
	}
	
	@GetMapping("/login/{id}/{pwd}") 
	public ResponseEntity<User> login(@PathVariable String id, @PathVariable String pwd) {
		return new ResponseEntity<User>(service.login(id, pwd), HttpStatus.OK);
	}

}
