package com.projectA1.service;

import org.springframework.stereotype.Service;

import com.projectA1.model.User;
import com.projectA1.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private UserRepository userRepository;
	
	//user 추가
	public void join(User user) {
		userRepository.save(user);
	}

	//user 상세보기
	public User view(String username) {
		return userRepository.findByUsername(username);
	}
	
	//user 수정 (더티체킹) => 전화번호, 주소, 이메일, 비밀번호 수정가능
	@Transactional
	public void update(User user) {

		User u = userRepository.findByUsername(u.getUserName());
		u.setPhoneNumber(u.getPhoneNumber());
		u.setAddr(u.getAddr()); // 주소
		u.setEmail(u.getEmail()); //이메일
		u.setPassword(u.getPassword()); //비밀번호
	}

	//user 삭제
	public void delete(String username) {
		userRepository.delete(username);
		
	}
}
