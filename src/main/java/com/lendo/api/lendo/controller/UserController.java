package com.lendo.api.lendo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lendo.api.lendo.advice.ResponseEnvelope;
import com.lendo.api.lendo.dto.LoginRequestDTO;
import com.lendo.api.lendo.exception.BusinessException;
import com.lendo.api.lendo.feign.FeignClientInterface;
import com.lendo.api.lendo.response.JwtResponse;
import com.lendo.api.lendo.response.Message;
import com.lendo.api.lendo.security.jwt.JwtTokenService;


@RestController
@RequestMapping("/api/users")
public class UserController {
	
	 @Autowired
	 JwtTokenService tokenService;
	 
	 @Autowired
	 AuthenticationManager authenticationManager;

	@Autowired
	FeignClientInterface feignClient;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestDTO loginRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        String jwt =  tokenService.generateToken(authentication);
		
		return new ResponseEntity<ResponseEnvelope>(ResponseEnvelope.builder().success(true).result(new JwtResponse(jwt)).build(),
				HttpStatus.OK);
	}

	
	@GetMapping()
	public ResponseEntity<?> getUsers() throws BusinessException {
		
		return new ResponseEntity<>(ResponseEnvelope.builder().success(true).result(Message.value("message.user.get.success"))
				.data(feignClient.getUsers()).build(),
				HttpStatus.OK);
	}
	
	@GetMapping("/{id}/posts")
	public ResponseEntity<?> getPostsByUser(@PathVariable() Long id) throws BusinessException {
		
		return new ResponseEntity<>(ResponseEnvelope.builder().success(true).result(Message.value("message.post.get.success"))
				.data(feignClient.getPostsByUser(id)).build(),
				HttpStatus.OK);
	}
}
