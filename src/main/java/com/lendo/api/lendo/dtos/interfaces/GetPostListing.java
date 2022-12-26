package com.lendo.api.lendo.dtos.interfaces;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface GetPostListing {

	@JsonProperty("id")
	public Long getId();

	@JsonProperty("title")
	public String getTitle();

	@JsonProperty("body")
	public String getBody();
	
	@JsonProperty("userId")
	public String getUserId();
	
	@JsonProperty("username")
	public String getUsername();
	
	@JsonProperty("creationDate")
	public String getCreationDate();

}
