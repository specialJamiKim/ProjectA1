package com.projectA1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectA1.model.Review;
import com.projectA1.repository.ReviewRepository;

import jakarta.transaction.Transactional;

@Service
public class ReviewService {
	
	@Autowired
    private ReviewRepository reviewRepository;
	
		@Transactional
	    public void addReview(Review review) {
	        reviewRepository.save(review);
	    }

	
	    public void deleteReview(Long id) {
	        reviewRepository.deleteById(id);
	    }

	
	    public Review getReviewById(Long id) {
	        return reviewRepository.findById(id).orElse(null);
	    }

	
	    public void updateReview(Review review) {
	        reviewRepository.save(review);
	    }


	    public List<Review> getAllReviews() {
	        return reviewRepository.findAll();
	    }
}
