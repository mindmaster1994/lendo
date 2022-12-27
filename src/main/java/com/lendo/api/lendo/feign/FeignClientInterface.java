package com.lendo.api.lendo.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value="feignClient", url="${feignClient.ribbon.listOfServers}")
public interface FeignClientInterface {
	
	@GetMapping("/users")
	List<Object> getUsers();
	
	@GetMapping("/posts")
	List<Object> getPosts();
	
	@GetMapping("/comments")
	List<Object> getComments();
	
	@GetMapping("/users/{id}/posts")
	List<Object> getPostsByUser(@PathVariable() Long id);
	
	@GetMapping("/posts/{id}/comments")
	List<Object> getCommentsByPost(@PathVariable() Long id);
	
}
