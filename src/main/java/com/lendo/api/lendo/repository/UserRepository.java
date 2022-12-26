package com.lendo.api.lendo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lendo.api.lendo.model.User;


@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
}