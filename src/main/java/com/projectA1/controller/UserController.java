package com.projectA1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projectA1.config.auth.PrincipalDetailService;
import com.projectA1.config.auth.PrincipalUser;
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

//	//사용자 추가폼 (변경완료)
//	@GetMapping("indijoin")
//	public String join() {
//		return "user/indijoin";
//	}
	
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
	
    // 사용자 마이페이지(상세보기)
	//이거 기준으로 짜면됩니다.
    @GetMapping("mypage")
    public String viewMyPage(@AuthenticationPrincipal PrincipalUser principalUser, Model model) {
        // 로그인된 사용자의 정보를 가져옵니다.
        User user = (User) principalUser.getUser();
        model.addAttribute("user", user);
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
