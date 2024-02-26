package com.projectA1.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
	@GetMapping("/")
	public String home() {
		return "main";
	}
	
	//로그인폼
    @GetMapping("/login/loginPage")
    public String showLoginPage() {
        return "/login/loginPage"; // 로그인 페이지의 Thymeleaf 템플릿 이름을 반환합니다.
    }
    
    
    //로그인 작업
    @PostMapping("loginPro")
    public String loginProcess() {
    	return "null";
    }
    
    
    //회원가입 폼(선택 user/owner)
    @GetMapping("/join/selectJoin")
    public String selectJoinForm()
    {
    	return "/join/selectJoin";
    }
    
    //user 회원가입 폼
    @GetMapping("/join/indijoin")
    public String selectUserJoinForm()
    {
    	return "/join/indijoin";
    }
    //owner 회원가입 폼
    @GetMapping("/join/ownerjoin")
    public String selectOwnerJoinForm()
    {
    	return "/join/ownerjoin";
    }
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @PostMapping("/loginPro")
//    public String loginProcess(@RequestParam String email, @RequestParam String password) {
//        try {
//            // 이메일을 기준으로 사용자 정보를 조회하여 UserDetails를 반환합니다.
//            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
//            
//            // 사용자의 입력 비밀번호를 인코딩하여 UserDetails에 있는 비밀번호와 비교합니다.
//            if (passwordEncoder.matches(password, userDetails.getPassword())) {
//                // 비밀번호가 일치하면 로그인 성공
//                // 로그인 성공 시 '/' 경로로 이동하도록 구현합니다.
//                return "redirect:/";
//            } else {
//                // 비밀번호가 일치하지 않으면 로그인 실패
//                // 로그인 실패 시 로그인 페이지로 다시 이동합니다.
//                return "redirect:/loginPage?error";
//            }
//        } catch (UsernameNotFoundException e) {
//            // 사용자를 찾을 수 없는 경우
//            // 로그인 실패 시 로그인 페이지로 다시 이동합니다.
//            return "redirect:/loginPage?error";
//        }
//    }
    
}