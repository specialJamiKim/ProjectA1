package com.projectA1.service;

import com.projectA1.model.Review;
import com.projectA1.model.User;
import com.projectA1.model.FitnessCenter;
import com.projectA1.repository.ReviewRepository;
import com.projectA1.repository.UserRepository;
import com.projectA1.repository.FitnessCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final FitnessCenterRepository fitnessCenterRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, FitnessCenterRepository fitnessCenterRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.fitnessCenterRepository = fitnessCenterRepository;
    }

    public Review saveReview(Long userId, Long centerId, int rating, String reviewText) {
        // 유효성 검사
        if (userId == null || centerId == null) {
            throw new IllegalArgumentException("사용자 ID와 센터 ID는 null일 수 없습니다.");
        }

        // 사용자와 피트니스 센터가 존재하는지 확인
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<FitnessCenter> centerOptional = fitnessCenterRepository.findById(centerId);

        if (userOptional.isEmpty() || centerOptional.isEmpty()) {
            throw new IllegalArgumentException("사용자 또는 피트니스 센터를 찾을 수 없습니다.");
        }

        User user = userOptional.get();
        FitnessCenter center = centerOptional.get();
        
        // 리뷰의 평점과 후기에 대한 유효성 검사 추가
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("평점은 1에서 5 사이어야 합니다.");
        }

        if (reviewText.length() < 10) {
            throw new IllegalArgumentException("후기는 10자 이상 작성해야 합니다.");
        }

        // 새 리뷰 작성
        Review review = new Review();
        review.setUser(user);
        review.setCenterNumber(center);
        review.setRating(rating);
        review.setReviewText(reviewText);
        
        // 리뷰 저장
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRepository.findByUserId(userId);
    }

    public List<Review> getReviewsByCenterId(Long centerId) {
        return reviewRepository.findByCenterId(centerId);
    }
}