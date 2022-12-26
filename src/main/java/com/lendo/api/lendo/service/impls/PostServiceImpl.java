package com.lendo.api.lendo.service.impls;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lendo.api.lendo.dtos.interfaces.GetPostListing;
import com.lendo.api.lendo.dtos.post.PostDTO;
import com.lendo.api.lendo.enumerations.ErrorCode;
import com.lendo.api.lendo.exception.BusinessException;
import com.lendo.api.lendo.model.Comment;
import com.lendo.api.lendo.model.Post;
import com.lendo.api.lendo.model.User;
import com.lendo.api.lendo.repository.PostRepository;
import com.lendo.api.lendo.response.Message;
import com.lendo.api.lendo.service.PostService;
import com.lendo.api.lendo.service.UserService;
import com.lendo.api.lendo.utils.Utils;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	UserService userService;
	
	@Override
	public Post getPostById(Long id) {
		Optional<Post> post = postRepository.findById(id);
		if(post.isPresent()) {
			return post.get();
		}
		return null;
	}

	@Override
	public void create(PostDTO create, User user) throws Exception {
		
		if(existsByTitle(create.getTitle())) {
			throw new BusinessException(Message.value("message.post.title.exists"), "",
					ErrorCode.INVALID_REQUEST.name());
		}
		
		Post post = new Post();
		post.setTitle(create.getTitle());
		post.setBody(create.getBody());
		post.setUserId(user.getId());
		
		postRepository.save(post);
	}
	
	public boolean existsByTitle(String title) {
		Optional<Post> post = postRepository.findByTitle(title);
		if(post.isPresent())
			return true;
		
		return false;
	}

	@Override
	public void delete(Long id) {
		postRepository.deleteById(id);	
	}

	public Page<GetPostListing> getListings(Optional<String> query, Integer pageNumber, Integer pageSize,
			Optional<String> sortBy, Optional<String> sortOrder) {
		
		Pageable paging = PageRequest.of(pageNumber, pageSize, Utils.sortBy(sortBy.get(), sortOrder.get()));
		
		String q = "";
		if(query.isPresent()) {
			q = query.get();
		}
		
		return postRepository.getListings(q, paging);
	}
	
	@Override
	public List<Post> getAll(){
		List<Post> posts = postRepository.findAll();
		
		return posts;
	}

	@Override
	public void addComment(Post post, Comment comment) {
		Set<Comment> comments = post.getComments();
		
		if(comments == null) {
			comments = new HashSet<Comment>();
		}
		
		comments.add(comment);
		
		post.setComments(comments);
		postRepository.save(post);	
	}

	@Override
	public List<Post> getPostsByUserId(Long id) {
		
		User user = userService.getUserById(id);
		if(user == null) {
			throw new BusinessException(Message.value("message.user.not.found"), "",
					ErrorCode.NOT_FOUND.name());
		}
		
		List<Post> posts = postRepository.findByUserId(id);
		return posts;
	}

	
}
