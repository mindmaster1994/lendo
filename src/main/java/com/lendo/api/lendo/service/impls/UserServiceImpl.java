package com.lendo.api.lendo.service.impls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lendo.api.lendo.dtos.auth.UserDTO;
import com.lendo.api.lendo.model.User;
import com.lendo.api.lendo.repository.UserRepository;
import com.lendo.api.lendo.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User getUserByEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		
		if(user.isPresent())
			return user.get();
		
		return null;
	}

	@Override
	public void create(UserDTO userDTO) {
		
		User existing = getUserByEmail(userDTO.getEmail());
		
		if(existing == null) {
			User user = new User();
			user.setUsername(userDTO.getUsername());
			user.setEmail(userDTO.getEmail());
			user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
			
			userRepository.save(user);
		}
	}
	
	@Override
	public void createTempUsers() {
		UserDTO user1 = new UserDTO();
		user1.setEmail("admin@gmail.com");
		user1.setPassword("admin123");
		user1.setUsername("Admin");
		
		create(user1);
	}

	@Override
	public void updateUser(User user) {
		userRepository.save(user);
	}

	@Override
	public List<User> getAll() {
		List<User> users = userRepository.findAll();
		
		return users;
	}

}
