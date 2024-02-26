package com.projectA1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projectA1.model.FitnessCenter;
import com.projectA1.repository.FitnessCenterRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FitnessCenterService {

	private final FitnessCenterRepository fitnesscenterrepository;
	
	// fitnesscenter 추가
	public void join(FitnessCenter fitnesscenter) {
		fitnesscenterrepository.save(fitnesscenter);
	}
	
	// fitnesscenter 상세보기
	public Optional<FitnessCenter> view(long id) {
        return fitnesscenterrepository.findById(id);
    }
	
	// fitnesscenter 전체보기
	 public List<FitnessCenter> viewAll() {
	        return fitnesscenterrepository.findAll();
	    }
	
	
	// fitnesscenter 수정 => 주소, 전화번호, 일일권, 오픈-마감시간 수정 가능
	@Transactional
	public void update(Long FitnessCenterId, FitnessCenter updatedFitnesscenter) {
		FitnessCenter f = fitnesscenterrepository.findById(FitnessCenterId).orElseThrow(() -> new EntityNotFoundException("FitnessCenter not found with id: " + FitnessCenterId));
		 f.setAddress(updatedFitnesscenter.getAddress());
		 f.setPhoneNumber(updatedFitnesscenter.getPhoneNumber());
		 f.setDailyPassPrice(updatedFitnesscenter.getDailyPassPrice());
		 f.setOpenTime(updatedFitnesscenter.getOpenTime());
		 f.setClosingTime(updatedFitnesscenter.getClosingTime());
	}
	
	
	// fitnesscenter 삭제
	 public void deleteFitnessCenter(Long id) {
	        fitnesscenterrepository.deleteById(id);
	    }


}
