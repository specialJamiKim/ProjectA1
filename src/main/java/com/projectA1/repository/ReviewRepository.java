package com.projectA1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectA1.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long>{
	
	// 사용자, centerid로 리뷰 조회시 필요(??)
	List<Review> findByUserId(Long userId);

    List<Review> findByCenterId(Long centerId);
}
