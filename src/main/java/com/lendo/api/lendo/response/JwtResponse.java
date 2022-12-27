package com.lendo.api.lendo.response;

import java.util.List;

import com.lendo.api.lendo.model.User;

import lombok.Data;

@Data
public class JwtResponse {
	private String token;
	private String type = "Bearer";

	public JwtResponse(String token) {
		super();
		this.token = token;
	}

}
