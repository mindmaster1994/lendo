package com.lendo.api.lendo.service.impls;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lendo.api.lendo.dtos.comment.CommentDTO;
import com.lendo.api.lendo.enumerations.ErrorCode;
import com.lendo.api.lendo.exception.BusinessException;
import com.lendo.api.lendo.model.Comment;
import com.lendo.api.lendo.model.Post;
import com.lendo.api.lendo.model.User;
import com.lendo.api.lendo.repository.CommentRepository;
import com.lendo.api.lendo.response.Message;
import com.lendo.api.lendo.service.CommentService;
import com.lendo.api.lendo.service.PostService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	PostService postService;
	
	@Override
	public Comment getCommentById(Long id) {
		Optional<Comment> comment = commentRepository.findById(id);
		if(comment.isPresent()) {
			return comment.get();
		}
		return null;
	}

	@Override
	public void create(CommentDTO create, User user) throws Exception {
		
		Post post = postService.getPostById(create.getPostId());
		
		if(post == null) {
			throw new BusinessException(Message.value("message.post.not.found"), "",
					ErrorCode.NOT_FOUND.name());
		}
		
		Comment comment = new Comment();
		comment.setTitle(create.getTitle());
		comment.setBody(create.getBody());
		comment.setUserId(user.getId());
		comment.setPost(post);
		commentRepository.save(comment);
	}

	@Override
	public void delete(Long id) {
		commentRepository.deleteById(id);	
	}

	@Override
	public List<Comment> getAll(){
		List<Comment> comments = commentRepository.findAll();
		
		return comments;
	}
	
	@Override
	public List<Comment> getCommentsByPostId(Long id) {
		
		Post post = postService.getPostById(id);
		
		if(post == null) {
			throw new BusinessException(Message.value("message.post.not.found"), "",
					ErrorCode.NOT_FOUND.name());
		}
		
		List<Comment> comments = post.getComments()
				.stream()
				.sorted((a,b)-> (a.getId().intValue() - b.getId().intValue()))
				.collect(Collectors.toList());
		
		return comments;
	}
}
