package com.api.rc_paradise_api.service;

import java.util.List;

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
	
	private final UserRepository repository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;


	public List<User> getAllUsers() {
		return repository.findAll();
	}
	
	public User getUser(String id) {
		return repository.findById(id).orElse(null);
	}
	
	public String registerUser(User user) {

		boolean userPresent = repository.findByphone(user.getPhone())
			.isPresent();
		if(userPresent){

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
	
	public User login(String id, String pwd) {
		User user = repository.findById(id).orElse(null);
		if(user == null) {
			return null;
		} else if (!user.getPassword().equals(pwd)) {
			return null;
		}else {
			return user;
		}
	}

    //METHOD by userDetailsService
	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

		return repository.findByphone(s)
				.orElseThrow(() ->
						new UsernameNotFoundException("User "+s+"not Found !!!!!"));
	}
}
