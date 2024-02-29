package com.projectA1.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

//	   @GetMapping("/")
//	    public String homePage(Model model) {
//	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	        String username = authentication.getName();
//	        model.addAttribute("username", username);
//	        return "main";
//	    }
	
	@GetMapping("/")
	public String homePage(Model model) {
	    // 현재 인증된 사용자 객체 가져오기
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    System.out.println("여기확인 >>" + authentication.getPrincipal());
	    
	    // 현재 인증된 사용자 객체 추가
	    model.addAttribute("authentication", authentication);

	    return "main";
	}

//	@GetMapping("/")
//	public String homePage(Model model) {
//        // 현재 인증된 사용자 객체 가져오기
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        // 현재 인증된 사용자 이름 가져오기
//        String username = authentication.getName();
//        
//        // 모델에 현재 인증된 사용자 이름 추가
//        model.addAttribute("username", username);
//        // 현재 인증된 사용자 객체 출력
//
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
   

    // 임시 세션 초기화
    @GetMapping("/sessionReset")
    public String sessionReset(HttpServletRequest request) {
        HttpSession session = request.getSession();
        // 세션 초기화
        session.invalidate();
        // 초기화된 후에 어디로 이동할지 리다이렉트할 URL을 반환합니다.
        return "redirect:/"; // 세션을 초기화한 후 메인 페이지로 리다이렉트할 수 있습니다. 필요에 따라 다른 경로를 지정할 수 있습니다
    }
    
}