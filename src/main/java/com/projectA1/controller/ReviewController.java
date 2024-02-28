package com.projectA1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projectA1.model.Review;
import com.projectA1.service.ReviewService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/review/*")
public class ReviewController {
<<<<<<< Updated upstream
 
=======
	
	private final ReviewService reviewService;
	
	@GetMapping("reviewList/{id}")
	public HashMap<String,Object> list(@PathVariable Long id ){
		List<Review> rList = reviewService.list(id);
		return null;
	}
	
	
>>>>>>> Stashed changes
}
