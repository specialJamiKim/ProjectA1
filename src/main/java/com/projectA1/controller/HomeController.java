package com.projectA1.controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

	@GetMapping("/")
	public String homePage() {
		return "main";
	}
	//로그아웃
	@GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login/loginPage";
    }
	
//	@GetMapping("/")
//	public String home(Model model) {
//	    // 현재 인증된 사용자 정보 가져오기
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        boolean isLoggedIn = authentication.isAuthenticated(); // 로그인 여부 확인
//
//        if (isLoggedIn) {
//            String username = authentication.getName(); // 현재 사용자의 이름 가져오기
//            model.addAttribute("username", username); // 모델에 현재 사용자의 이름 추가
//        }
//        model.addAttribute("isLoggedIn", isLoggedIn);
//		return "main";
//	}
	
	
	//로그인폼
    @GetMapping("/login/loginPage")
    public String showLoginPage() {
        return "/login/loginPage"; // 로그인 페이지의 Thymeleaf 템플릿 이름을 반환합니다.
    }
      
    //회원가입 폼(선택 user/owner)
    @GetMapping("/join/selectJoin")
    public String selectJoinForm()
    {
    	return "/join/selectJoin";
    }
    
    //user 회원가입 폼
    @GetMapping("/join/indiJoin")
    public String selectUserJoinForm()
    {
    	return "/join/indiJoin";
    }
    //owner 회원가입 폼
    @GetMapping("/join/ownerJoin")
    public String selectOwnerJoinForm()
    {
    	return "/join/ownerJoin";
    }

    
}