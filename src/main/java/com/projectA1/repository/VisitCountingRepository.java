package com.projectA1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectA1.model.VisitCounting;

public interface VisitCountingRepository extends JpaRepository<VisitCounting, long>{
	
}
