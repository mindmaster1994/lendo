package com.lendo.api.lendo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "users")
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User{

	public User() {}
	public User(Long id, String email, String password, String username) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.username = username;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name="username", length = 40)
	String username;
	
	@Column(name="email", length = 45)
	String email;
	
	@JsonIgnore
	@Column(name="password")
	String password;
	
}
