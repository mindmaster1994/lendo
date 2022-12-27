package com.lendo.api.lendo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lendo.api.lendo.advice.ResponseEnvelope;
import com.lendo.api.lendo.exception.BusinessException;
import com.lendo.api.lendo.feign.FeignClientInterface;
import com.lendo.api.lendo.response.Message;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	
	@Autowired
	FeignClientInterface feignClient;
	
	@GetMapping()
	public ResponseEntity<?> getComments() throws BusinessException {
		
		return new ResponseEntity<>(ResponseEnvelope.builder().success(true).result(Message.value("message.comment.get.success"))
				.data(feignClient.getComments()).build(),
				HttpStatus.OK);
	}
}
