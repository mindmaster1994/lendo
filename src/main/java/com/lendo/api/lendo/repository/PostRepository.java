package com.lendo.api.lendo.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lendo.api.lendo.dtos.interfaces.GetPostListing;
import com.lendo.api.lendo.model.Post;

@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post, Long> {
	
	String LISTING_QUERY = "SELECT t1.id AS id, t1.title AS title, t1.body AS body, t1.user_id AS userId, t1.created_at AS creationDate, t2.username AS username FROM lendo_db.posts t1 "
			+ "JOIN lendo_db.users t2 ON(t1.user_id=t2.id) WHERE lower(t1.title) LIKE %?%";
	String LISTING_QUERY_CONT = "select COUNT(*) from ( " + LISTING_QUERY + " ) as rows_count";

	@Query(value = LISTING_QUERY, countQuery = LISTING_QUERY_CONT, nativeQuery = true)
	Page<GetPostListing> getListings(String q, Pageable pageable);

	public Optional<Post> findByTitle(String title);
	
}