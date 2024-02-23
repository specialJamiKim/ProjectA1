package com.projectA1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projectA1.model.Review;
import com.projectA1.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {
	
	private final ReviewRepository reviewRepository;
	
	//전체보기
	public List<Review> list(Long id) {
		return reviewRepository.findByid(id);
	}

}
