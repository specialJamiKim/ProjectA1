package com.projectA1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.projectA1.model.FitnessCenter;
import com.projectA1.model.Review;
import com.projectA1.model.ReviewData;
import com.projectA1.model.User;
import com.projectA1.service.FitnessCenterService;
import com.projectA1.service.ReviewService;
import com.projectA1.service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/m_review/*")
@RequiredArgsConstructor
@Log4j2
public class M_ReviewController {

	private final ReviewService reviewService;
	private final FitnessCenterService fitnessCenterService;
	private final UserService userService;

	// 댓글 추가
	@PostMapping("add")
	public ResponseEntity<String> addReview(@RequestBody ReviewData review) {
		System.out.println("텍스트 >> " + review.getReviewText());
		System.out.println("userid >> " + review.getUserId());
		System.out.println("centerid >> " + review.getCenterId());
		System.out.println(review.getRating());

		User user = userService.findById(review.getUserId()).orElse(null);
		FitnessCenter center = fitnessCenterService.findByCenter(review.getCenterId()).orElse(null);
		
		Review saveReview = new Review();
		
		saveReview.setUser(user);
		saveReview.setCenter(center);
		saveReview.setRating(review.getRating());
		saveReview.setReviewText(review.getReviewText());
		// Review 저장
		reviewService.addReview(saveReview);
		return ResponseEntity.ok("success");
	}

	// 댓글 삭제
	@GetMapping("/delete/{id}")
	public ResponseEntity<String> deleteReview(@PathVariable Long id) {
		reviewService.deleteReview(id);
		return ResponseEntity.ok("success");
	}

	// 댓글 수정폼
	@GetMapping("/edit/{id}")
	public ResponseEntity<Review> editReview(@PathVariable Long id, Model model) {
		Review review = reviewService.getReviewById(id);
		return ResponseEntity.ok().body(review);
	}

	// 댓글 수정
	@PostMapping("/edit")
	public ResponseEntity<String> updateReview(@RequestBody Review review) {
		reviewService.updateReview(review);
		return ResponseEntity.ok("success");
	}

	// 댓글 전체보기
/*	@GetMapping("all")
	public ResponseEntity<List<Review>> getAllReviews() {
		List<Review> reviews = reviewService.getAllReviews();
		return ResponseEntity.ok().body(reviews);
	}*/
	
	@GetMapping("all/{centerId}")
    public ResponseEntity<?> getAllReviews(@PathVariable Long centerId) {
       
       List<Review> reviews = reviewService.findByCenterId(centerId);
       
       List<ReviewData> reviewDatas = new ArrayList<>();
       int i = 0;
       for (Review review : reviews) {
    	   ReviewData r = new ReviewData(reviews.get(i).getUser().getId(), 
    			   reviews.get(i).getCenter().getId(),
    			   reviews.get(i).getRating(),
    			   reviews.get(i).getReviewText());
    	   reviewDatas.add(r);
    	}
       System.out.println("요청옴");
       
       for(ReviewData r : reviewDatas) {
    	   System.out.println(r.getReviewText());
       }
       
       return  ResponseEntity.ok(reviewDatas);
       //return ResponseEntity.ok().body(reviews);
    }

}
