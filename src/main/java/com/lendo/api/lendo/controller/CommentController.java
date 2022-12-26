package com.lendo.api.lendo.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lendo.api.lendo.advice.ResponseEnvelope;
import com.lendo.api.lendo.dtos.comment.CommentDTO;
import com.lendo.api.lendo.response.Message;
import com.lendo.api.lendo.service.CommentService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/comments")
public class CommentController extends BaseController {

	@Autowired
	CommentService commentService;
	
	@PostMapping()
	public ResponseEntity<?> create(@Valid @RequestBody CommentDTO payload) throws Exception {
		commentService.create(payload, getCurrentUser());
		
		return new ResponseEntity<ResponseEnvelope>(ResponseEnvelope.builder()
				.success(true)
				.result(Message.value("message.comment.create.success"))
				.build(),
				HttpStatus.CREATED);
	}
	
	@DeleteMapping()
	public ResponseEntity<?> delete(@RequestParam() Long id) throws Exception {
		commentService.delete(id);
		
		return new ResponseEntity<ResponseEnvelope>(
				ResponseEnvelope.builder().success(true).result(Message.value("message.comment.delete.success")).build(),
				HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<?> getAll() throws Exception {
	
		return new ResponseEntity<ResponseEnvelope>(ResponseEnvelope.builder()
				.success(true)
				.result(Message.value("message.comment.get.success"))
				.data(commentService.getAll()).build(),
				HttpStatus.OK);
	}
	
	
}
