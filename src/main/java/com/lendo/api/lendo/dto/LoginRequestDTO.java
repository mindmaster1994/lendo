package com.lendo.api.lendo.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {

	private String email;
	
	private String password;
}
