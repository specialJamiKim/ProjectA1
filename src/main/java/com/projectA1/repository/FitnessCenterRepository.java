package com.projectA1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectA1.model.FitnessCenter;

@Repository
public interface FitnessCenterRepository extends JpaRepository<FitnessCenter, Long>{

	// 맞는지 모르겠어서.... 일단 추가만 해둬요..,.,.,.ㅠ
	 List<FitnessCenter> findByOwnerBusinessRegistrationNumber(String businessRegistrationNumber);
}
