package com.projectA1.service;

import java.util.List;
import java.util.Optional;

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
	
    //오너 id 이용, 센터이름 찾기
    public String findByCenterName(Long id) {
    	FitnessCenter center = fitnessCenterRepository.findById(id).get(); 
    	String centerName = center.getName();
    	
    	return centerName;
    }
	
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

	public Optional<FitnessCenter> findByCenter(Long centerId) {
		// TODO Auto-generated method stub
		return fitnessCenterRepository.findById(centerId);
	}


}
