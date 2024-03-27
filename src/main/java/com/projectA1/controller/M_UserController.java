package com.projectA1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.projectA1.config.auth.PrincipalUser;
import com.projectA1.model.FitnessCenter;
import com.projectA1.model.Reservation;
import com.projectA1.model.User;
import com.projectA1.service.DiaryService;
import com.projectA1.service.FitnessCenterService;
import com.projectA1.service.OwnerService;
import com.projectA1.service.ReservationService;
import com.projectA1.service.UserService;
import com.projectA1.service.VisitCountingService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/m_user/*")	// 주소를 요청했을 때 모든 것에 반응
@RequiredArgsConstructor
@Log4j2
public class M_UserController {

	// 사용자 추가
	// 사용자 마이페이지 => 정보수정, 회원탈퇴

	private final UserService userService;
	private final OwnerService ownerService;
	private final VisitCountingService visitCountingService;
	private final ReservationService reservationService;
	private final FitnessCenterService fitnessCenterService;
	private final DiaryService diaryService;
	
	// 사용자 추가 => 추가 후, 로그인 페이지
	@PostMapping("join")
	public ResponseEntity<String> join(@RequestBody User user) {
		    // 사용자 역할 설정
		    List<String> roles = new ArrayList<>();
		    roles.add("ROLE_USER");
		    user.setRole(roles);
		    
		    // 사용자 추가
		    userService.join(user);
	    	    
	    return ResponseEntity.ok("success"); // 사용자 추가 성공 시 성공 반환
	}

	// 사용자 마이페이지(상세보기) => 예약자 리스트도 표시
	// 이거 기준으로 짜면됩니다.
	@GetMapping("mypage")
	public ResponseEntity<String> viewMyPage(@AuthenticationPrincipal PrincipalUser principalUser, @RequestParam String email) {
		// 로그인된 사용자의 정보를 가져옵니다.
		//User user = (User) principalUser.getUser();
		User user2 = userService.findByEmail(email);
				
		List<Reservation> reservations = reservationService.findByUserId(user2.getId());
		// 센터 방문횟수를 가져와 화면에 표시(userid, centerid)
		long visitCount = visitCountingService.visitCounting(user2.getId());
		
		
		
		return ResponseEntity.ok("/user/mypage");
	}

	// 사용자 정보수정폼(조회)
	@GetMapping("updateForm")
	public ResponseEntity<User> updateForm(@AuthenticationPrincipal PrincipalUser principalUser) {
		// 로그인된 사용자의 정보를 가져옵니다.
		User user = (User) principalUser.getUser();
		return ResponseEntity.ok().body(user);
	}

	// 사용자 정보수정 => ajax 비동기 처리
	@PostMapping("update")
	// updateUser -> ajax 받아온 데이터 user
	public ResponseEntity<User> update(@AuthenticationPrincipal PrincipalUser principalUser, @RequestBody User updateUser) {
		User currentUser = (User) principalUser.getUser();
		userService.update(currentUser, updateUser);
		return ResponseEntity.ok().body(currentUser);
	}
	
	// 사용자 회원탈퇴
	@Transactional
	@DeleteMapping("delete")
	public ResponseEntity<String> delete(@AuthenticationPrincipal PrincipalUser principalUser, HttpServletRequest request,
			HttpServletResponse response) {
		User user = (User) principalUser.getUser();

		userService.delete(user.getEmail());
		return ResponseEntity.ok("success");
	}
	
	
	//테스트용임 지워도 됩니다.
    private Gson gson = new GsonBuilder().create();

    // 테스트용 엔드포인트
    @GetMapping("/tototo")
    public ResponseEntity<String> testSend() {
        User user = new User();
        user.setName("kimkim");
        user.setEmail("kdsf@gmail.com");
        String json = gson.toJson(user);
        return ResponseEntity.ok(json);
    }


}