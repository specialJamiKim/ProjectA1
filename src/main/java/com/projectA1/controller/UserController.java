package com.projectA1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projectA1.config.auth.PrincipalDetailService;
import com.projectA1.model.User;
import com.projectA1.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
public class UserController {
	
	//사용자 추가
	//사용자 마이페이지 => 정보수정, 회원탈퇴
	
	private final UserService userService;
	
//    @Autowired
//    private PrincipalDetail principalDetail; // 사용자 인증 서비스
//
//    @PostMapping("/loginPro") // 로그인 폼에서 제출된 데이터를 처리하는 엔드포인트
//    public String loginProcess(@RequestParam("email") String email, @RequestParam("password") String password) {
//        // PrincipalDetail을 사용하여 사용자 인증 정보를 가져오기
//        UserDetails d= principalDetail.loadUserByUsername(email);
//        System.out.println(d.getUsername());
//        
//        // 로그인 성공 후 리다이렉트할 경로를 반환 (여기서는 "/"로 설정)
//        return "redirect:/";
//    }

	//사용자 추가폼 (변경완료)
	@GetMapping("join")
	public String join() {
		return "user/indijoin";
	}
	
	//사용자 추가 => 추가 후, 로그인 페이지
	@PostMapping("join")
	@ResponseBody
	public String join(@RequestBody User user) {
		List<String> roles = new ArrayList<>();
		roles.add("ROLE_USER");
		user.setRole(roles);
		userService.join(user);
		return "success";
	}
	
	//사용자 마이페이지(상세보기)
	@GetMapping("view/{id}")
	public String view(@PathVariable Long id, Model model) {
		model.addAttribute("user", userService.view(id));
		return "/user/mypage";
	}
	
	//사용자 정보수정폼
	@GetMapping("update/{id}")
	public String update(@PathVariable Long id  , Model model) {
		model.addAttribute("user", userService.view(id));
		return "/user/update";
	}
	
	//사용자 정보수정
	@PostMapping("update")
	public String update(User user) {
		userService.update(user.getId(),user);
		return "/user/mypage/"+ user.getId();
	}
	
	//사용자 회원탈퇴
	@GetMapping("delete/{username}")
	public String delete(@PathVariable Long id) {
		userService.delete(id);
		return "success";
	}
}
