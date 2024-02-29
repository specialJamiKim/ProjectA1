package com.projectA1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projectA1.model.FitnessCenter;
import com.projectA1.repository.FitnessCenterRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class FitnessCenterService {

	private final FitnessCenterRepository fitnessCenterRepository;
	
	// 추가
	public void join(FitnessCenter fitnesscenter) {
		fitnessCenterRepository.save(fitnesscenter);
	}
	
	//  상세보기
	@Transactional
	public FitnessCenter view(Long id) {
		FitnessCenter fitnessCenter = fitnessCenterRepository.findById(id).get();
        return fitnessCenter;
    }
	
	//  전체보기
	 public List<FitnessCenter> viewAll() {
	        return fitnessCenterRepository.findAll();
	    }
	
	
	//  수정 => 주소, 전화번호, 일일권 수정가능
	@Transactional
	public void update(FitnessCenter fitnesscenter) {
		FitnessCenter f = fitnessCenterRepository.findById(fitnesscenter.getId()).get();
		f.setAddress(fitnesscenter.getAddress());
		f.setPhoneNumber(fitnesscenter.getPhoneNumber());
		f.setOpenTime(fitnesscenter.getOpenTime());
		f.setClosingTime(fitnesscenter.getClosingTime());
	}
	
	
	// 삭제
	@Transactional
	 public void deleteFitnessCenter(Long id) {
		fitnessCenterRepository.deleteById(id);
	    }


}
