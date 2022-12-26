package com.lendo.api.lendo.model;

import java.time.Instant;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
@Entity
@Data
@Table(name = "posts")
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name="title")
	String title;
	
	@Column(name="body")
	String body;
	
	@Column(name="user_id")
	Long userId;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	@OneToMany(mappedBy="post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	Set<Comment> comments;
	
	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	Instant createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	Instant updatedAt;
	
}
