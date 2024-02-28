package com.projectA1.service;

import org.springframework.stereotype.Service;

import com.projectA1.repository.VisitCountingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VisitCountingService {

	private final VisitCountingRepository visitCountingRepository;
	
	//userId로 해당 center 몇번 방문했는지 
	public int visitCounting(Long userId) {
		return visitCountingRepository.countByUserId(userId);
	}
}
