package com.projectA1.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.projectA1.model.Reservation;
import com.projectA1.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationRepository reservationRepository;
	
	
	//예약 생성
	public void create(Reservation reservation) {
		reservationRepository.save(reservation);
	}
	
	//예약 수정
//	public void update(Reservation reservation) {
//		reservationRepository.
//	}
	
	//예약 삭제
	public void delete(Long reservationId) {
		reservationRepository.deleteById(reservationId);
	}
	
	//자정이 되면 전날 예약 삭제
	// 매일 자정에 실행되는 메서드
    @Scheduled(cron = "0 0 0 * * *")
    public void deletePreviousDayReservations() {
        // 자정 전의 시간을 구합니다.
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime midnight = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);

        // 전날 자정 시간을 구합니다.
        LocalDateTime previousMidnight = midnight.minusDays(1);

        // 전날 자정 이전의 예약들을 가져옵니다.
        List<Reservation> previousDayReservations = reservationRepository.findByReservationTimeBefore(previousMidnight);

        // 가져온 예약들을 삭제합니다.
        reservationRepository.deleteAll(previousDayReservations);
    }

    //유저의 예약확인
	public List<Reservation> findByUserId(Long id) {
		// TODO Auto-generated method stub
		return reservationRepository.findAllByUserId(id);
	}

	
	//예약정보 찾기
	public Optional<Reservation> findReservation(Long reservationId) {
		// TODO Auto-generated method stub
		return reservationRepository.findById(reservationId);
	}

}
