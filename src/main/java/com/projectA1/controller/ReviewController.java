package com.projectA1.controller;

import com.projectA1.model.Review;
import com.projectA1.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/reviews/*")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // POST : 리뷰 저장
    @PostMapping
    public String saveReview(
            @RequestParam Long userId,
            @RequestParam Long centerId,
            @RequestParam int rating,
            @RequestParam String reviewText,
            Model model) {
        try {
            Review savedReview = reviewService.saveReview(userId, centerId, rating, reviewText);
            model.addAttribute("review", savedReview);
            return "reviewResult"; // 리뷰 저장 후 뜨는 페이지
        } catch (IllegalArgumentException e) {
            return "errorPage"; // 검사 오류 시 뜨는 페이지
        }
    }

    // GET : 리뷰 전체 조회
    @GetMapping
    public String getAllReviews(Model model) {
        List<Review> reviews = reviewService.getAllReviews();
        model.addAttribute("reviews", reviews);
        return "allReviews"; // 전체 리뷰 조회 후 뜨는 페이지
    }

    // 사용자 리뷰 조회
    @GetMapping("/user/{userId}")
    public String getReviewsByUserId(@PathVariable Long userId, Model model) {
        List<Review> reviews = reviewService.getReviewsByUserId(userId);
        model.addAttribute("reviews", reviews);
        return "userReviews"; // 사용자 리뷰 조회 후 뜨는 페이지
    }

    // 센터 리뷰 조회
    @GetMapping("/center/{centerId}")
    public String getReviewsByCenterId(@PathVariable Long centerId, Model model) {
        List<Review> reviews = reviewService.getReviewsByCenterId(centerId);
        model.addAttribute("reviews", reviews);
        return "centerReviews"; // 센터 리뷰 조회 후 뜨는 페이지
    }
}