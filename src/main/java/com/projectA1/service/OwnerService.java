package com.projectA1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectA1.model.Owner;
import com.projectA1.model.User;
import com.projectA1.repository.OwnerRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class OwnerService {
	
	@Autowired
	private OwnerRepository ownerRepository;

	//중복확인
	
	//추가
	public void join(Owner owner) {
		ownerRepository.save(owner);
	}
	
	//마이페이지(상세보기)
	public Optional<Owner> view(long id) {
		return ownerRepository.findById(id);
	}
	
	//수정 (더티체킹) ==> 전화번호, 주소, 사업자 이름
	@Transactional
	public void update(long id, Owner updatedOwner)
	{
	    Owner owner = ownerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
	    // 변경된 필드들만 세팅
//	    owner.setOwnerPhoneNumber(updatedOwner.getOwnerPhoneNumber()); //전화번호
//	    owner.setCenterAddr(updatedOwner.getCenterAddr()); //주소
//	    owner.setOwnerName(updatedOwner.getOwnerName()); //이름    
	}
	//삭제
	public void delete(Long id) {
		ownerRepository.deleteById(id);
	}
}
