package com.api.rc_paradise_api.service;

import java.util.List;
import java.util.Optional;

import com.api.rc_paradise_api.model.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.rc_paradise_api.model.User;
import com.api.rc_paradise_api.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

	@Autowired
	private final UserRepository repository;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;



	public List<User> getAllUsers() {
		return repository.findAll();
	}
	
	public User getUser(String id) {
		return repository.findById(id).orElse(null);
	}
	
	public String registerUser(User user) {

		User user1 = repository.findByPhone(user.getPhone());

		if(user1 != null){

			throw  new IllegalStateException("User is already registered ");
		}

		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword); //encoding the password

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
	
	public User login(String phone) {
		User user = repository.findByPhone(phone);
		if(user == null) {
			return null;
		}
		return user;

	}

    //METHOD by userDetailsService
	@Override
	public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {

		User user = repository.findByPhone(phone);
		if(user == null){

			throw new UsernameNotFoundException("User Not found");
		}

		return new CustomUserDetails(user);

	}
}
