package com.projectA1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectA1.model.VisitCounting;

public interface VisitCountingRepository extends JpaRepository<VisitCounting, Long>{

	// userIdf를 기반으로 방문 횟수를 카운트하는 메서드
	int countByUserId(Long userId);
	
}
