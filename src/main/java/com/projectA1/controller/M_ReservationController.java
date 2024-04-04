package com.projectA1.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projectA1.config.auth.PrincipalUser;
import com.projectA1.model.FitnessCenter;
import com.projectA1.model.Reservation;
import com.projectA1.model.User;
import com.projectA1.model.VisitCounting;
import com.projectA1.service.ReservationService;
import com.projectA1.service.VisitCountingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/m_reservation/*")
@RequiredArgsConstructor
public class M_ReservationController {

	private final ReservationService reservationService;
	private final VisitCountingService visitCountingService; // 방문횟수 저장

	// 예약 리스트 클라이언트 반환
	@PostMapping("/list")
	public ResponseEntity<List<Reservation>> getUserReservations(@RequestParam("userId") Long userId) {
	    // userId를 이용하여 해당 사용자의 예약 리스트를 조회하고 반환하는 코드를 작성해야 합니다.
	    List<Reservation> reservations = reservationService.findByUserId(userId); // 예약 리스트를 조회하는 코드 작성

	    for(int i = 0; i < reservations.size(); i++) {
	        System.out.println(reservations.get(i).getCenter().getName());
	    }

	    return ResponseEntity.ok(reservations);
	}


	// 예약 등록
	@PostMapping("create")
	public ResponseEntity<String> createReservation(@RequestBody Reservation reservation) {
//	      Date reservationTime = parseDateString(reservation.getReservationTime().toString());
//	      reservation.setReservationTime(reservationTime);
		// 예약 서비스를 통해 예약 생성
		System.out.println(reservation.getId());
		System.out.println(reservation.getCenter().getId());
		System.out.println(reservation.getUser().getId());
		System.out.println(reservation.getReservationTime());
		reservationService.create(reservation);
		return ResponseEntity.ok("success");
	}

	// 예약지점 상세보기
	@GetMapping("view/{reservationId}")
	public ResponseEntity<String> view(@PathVariable Long reservationId, Model model) {
		model.addAttribute("reservation", reservationService.findReservation(reservationId));
		return ResponseEntity.ok("redirect:/reservation/view");
	}

	// 예약 취소
	@GetMapping("delete/{reservationId}")
	public ResponseEntity<String> delete(@PathVariable Long reservationId) {
		reservationService.delete(reservationId);
		return ResponseEntity.ok("redirect:/user/mypage");
	}

	// 예약 수정폼(모달로 처리)
//	@GetMapping("update/{reservationId}")
//	public String update(@PathVariable Long reservationId, Model model) {
//		Optional<Reservation> reservation = reservationService.findReservation(reservationId);	
//		model.addAttribute("reservation",reservation.get());
//		return "/reservation/updateForm";
//	}
//	
//	//예약수정
//	@PutMapping("update/{reservationId}")
//	@ResponseBody
//	public String update(@PathVariable Long reservationId, @RequestParam Date newReservationTime) {
//		Optional<Reservation> reservation = reservationService.findReservation(reservationId);
//		
//		reservation.get().setReservationTime(newReservationTime);
//		reservationService.create(reservation.get());
//		return "success";
//	}

	// 예약 사용함(임시로 만들어둔 버튼)
	@GetMapping("used/{reservationId}")
	public ResponseEntity<String> used(@PathVariable Long reservationId) {
		Optional<Reservation> reservation = reservationService.findReservation(reservationId);
		User user = reservation.get().getUser();
		FitnessCenter center = reservation.get().getCenter();

		// 유저와 센터 기록 존재 여부 파악위함
		VisitCounting visitCounting = new VisitCounting(user, center);

//		// 예약한 기록이 있다면 카운트 +1 만 추가해주기
//		if (visitCounting != null) {
//			count = visitCounting.getVisitCount();
//			visitCounting.setVisitCount(count + 1);
//		} else
//		// 아니라면 새롭게 만들어서 추가해주기
//		{
//			visitCounting = new VisitCounting(user, center, count + 1);
//		}

		// 방문기록
		visitCountingService.visit(visitCounting);
		// 예약기록 삭제
		reservationService.delete(reservationId);
		return ResponseEntity.ok("redirect:/user/mypage");
	}

}
