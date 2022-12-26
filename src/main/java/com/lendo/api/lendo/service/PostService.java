package com.lendo.api.lendo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import com.lendo.api.lendo.dtos.interfaces.GetPostListing;
import com.lendo.api.lendo.dtos.post.PostDTO;
import com.lendo.api.lendo.model.Comment;
import com.lendo.api.lendo.model.Post;
import com.lendo.api.lendo.model.User;

public interface PostService {
	
	public Post getPostById(Long id);
	
	public List<Post> getAll();
	
	public void create(PostDTO create, User user) throws Exception;
	
	public void delete(Long id);
	
	public void addComment(Post post, Comment comment);
	
	public Page<GetPostListing> getListings(Optional<String> query, Integer pageNumber, Integer pageSize,
			Optional<String> sortBy, Optional<String> sortOrder);
}
