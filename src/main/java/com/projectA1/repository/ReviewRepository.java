package com.projectA1.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectA1.model.Review;
import com.projectA1.model.ReviewData;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long>{
	
	List<Review> findAllByUserId(Long userId);
	
	List<Review> findAllByCenterId(Long centerId);
	//리뷰 전체가져오기
	List<Review> findByCenterId(Long id);
	Page<Review> findByCenterId(Long centerId, Pageable pageable);
	
	
}
