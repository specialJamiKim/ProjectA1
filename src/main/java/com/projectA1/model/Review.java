package com.projectA1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 설정
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; //user id
    
    @ManyToOne
    @JoinColumn(name = "fitnessCenter_id", nullable = false)
    private FitnessCenter fitnessCenter; //센터번호

    private int rating; //평점
    private String reviewText; // 후기
}