package com.lendo.api.lendo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.lendo.api.lendo.model.Comment;

@Repository
@Transactional
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	
}