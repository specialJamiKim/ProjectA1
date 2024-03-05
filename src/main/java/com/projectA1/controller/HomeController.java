package com.projectA1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.projectA1.repository.OwnerRepository;
import com.projectA1.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Controller
public class HomeController {
	
    @Autowired
    private UserRepository userRepository; // UserRepository 주입

    @Autowired
    private OwnerRepository ownerRepository; // OwnerRepository 주입

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // BCryptPasswordEncoder 주입
	
	//클릭시 암호화
	@GetMapping("/encrypt")
	@Transactional
	public String enenenen() {
        // 사용자의 비밀번호를 암호화하여 업데이트
        userRepository.findAll().forEach(user -> {
            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);
            userRepository.save(user);
        });

        // 소유자의 비밀번호를 암호화하여 업데이트
        ownerRepository.findAll().forEach(owner -> {
            String encryptedPassword = passwordEncoder.encode(owner.getPassword());
            owner.setPassword(encryptedPassword);
            ownerRepository.save(owner);
        });
        
        return "main";
	}
	
	
	@GetMapping("/")
	public String homePage() {
	    return "main";
	}
		
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