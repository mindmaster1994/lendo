package com.lendo.api.lendo.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User{

	public User() {}
	public User(Long id, String email, String password, String username) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.username = username;
	}

	Long id;
	
	String username;
	
	String email;
	
	String password;
}
