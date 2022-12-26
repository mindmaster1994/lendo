package com.lendo.api.lendo.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
@Entity
@Data
@Table(name = "comments")
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name="title")
	String title;
	
	@Column(name="body")
	String body;
	
	@Column(name="user_id")
	Long userId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;
	
	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	Instant createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	Instant updatedAt;
	
}
