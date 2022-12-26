package com.lendo.api.lendo.response;

import java.util.List;

import com.lendo.api.lendo.model.User;

import lombok.Data;

@Data
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private User user;

	public JwtResponse(String token, User user) {
		super();
		this.token = token;
		this.user = user;
	}

}
