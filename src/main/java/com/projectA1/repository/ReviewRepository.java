package com.projectA1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectA1.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long>{
	
	List<Review> findAllByUserId(Long userId);
	
}
