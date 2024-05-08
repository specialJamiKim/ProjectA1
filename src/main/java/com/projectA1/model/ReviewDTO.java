package com.projectA1.model;

import lombok.Data;

//userId, reviewId 사용하기 위한 객체 받아오는 DTO
@Data
public class ReviewDTO {
	private User user;
	private Review review;
}
