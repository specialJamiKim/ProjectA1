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

    @ManyToOne
    @JoinColumn(name = "review_id", nullable = false)
    private User user; //user id
    
    @ManyToOne
    @JoinColumn(name = "review_centerNumber", nullable = false)
    private FitnessCenter centerNumber; //센터번호

    private int rating; //평점
    private String reviewText; // 후기
}