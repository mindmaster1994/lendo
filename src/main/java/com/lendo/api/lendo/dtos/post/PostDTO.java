package com.lendo.api.lendo.dtos.post;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PostDTO {
	
	@NotBlank(message = "Title cannot be null/empty")
	private String title;

	@NotBlank(message = "Body cannot be null/empty")
	private String body;
	
}
