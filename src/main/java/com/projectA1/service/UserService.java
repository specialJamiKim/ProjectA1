package com.projectA1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectA1.model.User;
import com.projectA1.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	
	//user 추가
	public void join(User user) {
		userRepository.save(user);
	}

	//user 상세보기
	public Optional<User> view(long id) {
		return userRepository.findById(id);
	}
	
	//user 수정 (더티체킹) => 전화번호, 주소, 이메일, 비밀번호 수정가능
	@Transactional

	public void update(Long userId, User updatedUser) {
	    User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
	    // 변경된 필드들만 세팅
//	    user.setPhoneNumber(updatedUser.getPhoneNumber());
//	    user.setAddr(updatedUser.getAddr());
//	    user.setEmail(updatedUser.getEmail());
//	    user.setPassword(updatedUser.getPassword());
	}
	//user 삭제
	public void delete(Long id) {
		userRepository.deleteById(id);
	}
}
