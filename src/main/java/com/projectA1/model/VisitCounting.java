package com.projectA1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class VisitCounting {
	@ManyToOne
	@JoinColumn(name = "visit_centerNumber", nullable = false)
	private FitnessCenter centerNumber;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private int visitCount;
}