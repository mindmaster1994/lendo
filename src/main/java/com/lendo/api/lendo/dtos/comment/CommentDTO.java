package com.lendo.api.lendo.dtos.comment;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CommentDTO {
	
	@NotBlank(message = "Title cannot be null/empty")
	private String title;

	@NotBlank(message = "Body cannot be null/empty")
	private String body;
	
	@NotBlank(message = "Post Id cannot be null/empty")
	private Long postId;
	
}
