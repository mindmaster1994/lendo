package com.lendo.api.lendo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import com.lendo.api.lendo.dtos.comment.CommentDTO;
import com.lendo.api.lendo.model.Comment;
import com.lendo.api.lendo.model.User;

public interface CommentService {
	
	public Comment getCommentById(Long id);
	
	public List<Comment> getAll();
	
	public void create(CommentDTO create, User user) throws Exception;
	
	public void delete(Long id);

}
