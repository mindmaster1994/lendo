package com.lendo.api.lendo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lendo.api.lendo.advice.ResponseEnvelope;
import com.lendo.api.lendo.dtos.auth.LoginRequestDTO;
import com.lendo.api.lendo.dtos.auth.UserDTO;
import com.lendo.api.lendo.model.User;
import com.lendo.api.lendo.response.JwtResponse;
import com.lendo.api.lendo.response.Message;
import com.lendo.api.lendo.security.jwt.JwtUtils;
import com.lendo.api.lendo.security.service.UserDetailsImpl;
import com.lendo.api.lendo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	private UserService userService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		User user = userService.getUserByEmail(userDetails.getEmail());
		
		return new ResponseEntity<ResponseEnvelope>(ResponseEnvelope.builder().success(true).result(new JwtResponse(jwt, user)).build(),
				HttpStatus.OK);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO) {

		userService.create(userDTO);

		return new ResponseEntity<ResponseEnvelope>(
				ResponseEnvelope.builder().success(true).result(Message.value("message.user.create.success")).build(),
				HttpStatus.CREATED);
	}
	
	@GetMapping()
	public ResponseEntity<?> getAll() throws Exception {
	
		return new ResponseEntity<ResponseEnvelope>(ResponseEnvelope.builder()
				.success(true)
				.result(Message.value("message.user.get.success"))
				.data(userService.getAll()).build(),
				HttpStatus.OK);
	}
}
