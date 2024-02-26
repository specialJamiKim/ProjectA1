package com.projectA1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
    @Autowired
    private PrincipalDetailService principalDetailService;

    @GetMapping("/your-endpoint")
    public String yourHandlerMethod(Model model) {
        // 사용자 이름을 가져오기 위해 PrincipalDetailService를 사용
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = (User) principalDetailService.loadUserByUsername(email);
        
        // 사용자 이름을 모델에 추가하여 뷰로 전달
        model.addAttribute("username", user.getName());

        // 다른 작업 수행 또는 반환할 뷰 이름 반환
        return "/";
    }

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
