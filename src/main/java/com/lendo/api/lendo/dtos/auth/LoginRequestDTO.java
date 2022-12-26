package com.lendo.api.lendo.dtos.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LoginRequestDTO {
	
	@NotNull
	@NotBlank
	@Email
	private String email;
	
	@NotNull
	@NotBlank
	private String password;
}
