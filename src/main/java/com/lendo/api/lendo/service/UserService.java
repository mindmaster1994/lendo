package com.lendo.api.lendo.service;

import java.util.List;

import com.lendo.api.lendo.dtos.auth.UserDTO;
import com.lendo.api.lendo.model.User;

public interface UserService {
	
	public User getUserByEmail(String email);
	
	public User getUserById(Long id);
	
	public void create(UserDTO user);
	
	public void updateUser(User user);
	
	public List<User> getAll();
	
	public void createTempUsers();
	
}
