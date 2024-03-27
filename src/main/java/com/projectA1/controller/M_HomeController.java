package com.projectA1.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/m_home")
public class M_HomeController {
	
//	@Autowired
//	private DataLoader da;
//	
//	@GetMapping("/updatePassword")
//	public void updatePass() {
//		da.loadData();
//	}
	
	@GetMapping("/")
	public ResponseEntity<String> homePage() {
	    return ResponseEntity.ok().body("main");
	}
		
	//로그인폼
    @GetMapping("/login/loginPage")
    public ResponseEntity<String> showLoginPage() {
    	return ResponseEntity.ok().body("/login/loginPage"); // 로그인 페이지의 Thymeleaf 템플릿 이름을 반환합니다.
    }
      
    //회원가입 폼(선택 user/owner)
//    @GetMapping("/join/selectJoin")
//    public ResponseEntity<String> selectJoinForm()
//    {
//    	 return ResponseEntity.ok().body("/join/selectJoin");
//    }
    
    //user 회원가입 폼
    @GetMapping("/join/indiJoin")
    public ResponseEntity<String> selectUserJoinForm()
    {
    	  return ResponseEntity.ok().body("/join/indiJoin");
    }    
    
}