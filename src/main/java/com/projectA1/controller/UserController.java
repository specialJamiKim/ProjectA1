package com.projectA1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projectA1.model.User;
import com.projectA1.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user/*")
public class UserController {
	
	//사용자 추가
	//사용자 마이페이지 => 정보수정, 회원탈퇴
	
	@Autowired
	private UserService userService;
	
	//사용자 추가폼 변경완료
	@GetMapping("join")
	public String join() {
		return "/user/join";
	}
	//사용자 정보추가 => 추가 후, 로그인 페이지
	@PostMapping("join")
	public String join(User user) {
		userService.join(user);
		return "/user/login"; // 페이지 수정 필요
	}
	
	//사용자 마이페이지(상세보기)
	@GetMapping("view/{id}")
	public String view(@PathVariable String username, Model model) {
		model.addAttribute("user", userService.view(username));
		return "/user/view";
	}
	
	//사용자 정보수정폼
	@GetMapping("update/{username}")
	public String update(@PathVariable String username  ,Model model) {
		model.addAttribute("user", userService.view(username));
		return "/user/update";
	}
	
	//사용자 정보수정
	@PostMapping("update")
	public String update(User user) {
		userService.update(user);
		return "/user/view/"+ user.getUsername();
	}
	
	//사용자 회원탈퇴
	@GetMapping("delete/{username}")
	public String delete(@PathVariable String username) {
		userService.delete(username);
		return "success";
	}
}
