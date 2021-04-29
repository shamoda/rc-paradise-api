package com.api.rc_paradise_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.rc_paradise_api.model.User;
import com.api.rc_paradise_api.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository repository;

	@Autowired
	public UserService(UserRepository repository) {
		this.repository = repository;
	}
	
	public List<User> getAllUsers() {
		return repository.findAll();
	}
	
	public User getUser(String id) {
		return repository.findById(id).orElse(null);
	}
	
	public String registerUser(User user) {
		repository.save(user);
		return "Registration successful";
	}
	
	public String updateUser(User user) {
		repository.save(user);
		return "User updated successful";
	}
	
	public String deleteUser(String id) {
		repository.deleteById(id);
		return "User deleted successfully";
	}
	
	public User login(String id, String pwd) {
		User user = repository.findById(id).orElse(null);
		if(user == null) {
			return user;
		} else if (!user.getPassword().equals(pwd)) {
			return null;
		}else {
			return user;
		}
	}
	

}
