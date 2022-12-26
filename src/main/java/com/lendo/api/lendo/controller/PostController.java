package com.lendo.api.lendo.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lendo.api.lendo.advice.ResponseEnvelope;
import com.lendo.api.lendo.constant.Constant;
import com.lendo.api.lendo.dtos.post.PostDTO;
import com.lendo.api.lendo.exception.BusinessException;
import com.lendo.api.lendo.response.Message;
import com.lendo.api.lendo.service.PostService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/posts")
public class PostController extends BaseController {

	@Autowired
	PostService postService;
	
	@PostMapping()
	public ResponseEntity<?> create(@Valid @RequestBody PostDTO payload) throws Exception {
		postService.create(payload, getCurrentUser());
		
		return new ResponseEntity<ResponseEnvelope>(ResponseEnvelope.builder()
				.success(true)
				.result(Message.value("message.post.create.success"))
				.build(),
				HttpStatus.CREATED);
	}
	
	@DeleteMapping()
	public ResponseEntity<?> delete(@RequestParam() Long id) throws Exception {
		postService.delete(id);
		
		return new ResponseEntity<ResponseEnvelope>(
				ResponseEnvelope.builder().success(true).result(Message.value("message.post.delete.success")).build(),
				HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<?> getAll() throws Exception {
	
		return new ResponseEntity<ResponseEnvelope>(ResponseEnvelope.builder()
				.success(true)
				.result(Message.value("message.post.get.success"))
				.data(postService.getAll()).build(),
				HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getPostsByUserId(@PathVariable() Long id) throws Exception {
	
		return new ResponseEntity<ResponseEnvelope>(ResponseEnvelope.builder()
				.success(true)
				.result(Message.value("message.post.get.success"))
				.data(postService.getPostsByUserId(id)).build(),
				HttpStatus.OK);
	}
	
	@GetMapping("/listing")
	public ResponseEntity<?> getPostListing(@RequestParam(name = Constant.REQ_PARAM_Q) final Optional<String> query,
			@RequestParam(name = Constant.REQ_PARAM_PAGE) final Integer pageNumber,
			@RequestParam(name = Constant.REQ_PARAM_SIZE) final Integer pageSize,
			@RequestParam(name = Constant.REQ_PARAM_SORT) final Optional<String> sortBy,
			@RequestParam(name = Constant.REQ_PARAM_ORDER) final Optional<String> sortOrder) throws BusinessException {
		
		log.debug("REST request to get Post Listing : {}", query, pageNumber, pageSize, sortBy, sortOrder);
		
		return new ResponseEntity<>(ResponseEnvelope.builder().success(true).result(Message.value("message.post.get.success"))
				.data(postService.getListings(query, pageNumber, pageSize, sortBy, sortOrder)).build(),
				HttpStatus.OK);
	}
}
