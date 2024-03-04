package com.projectA1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projectA1.model.FitnessCenter;
import com.projectA1.model.User;
import com.projectA1.model.VisitCounting;

public interface VisitCountingRepository extends JpaRepository<VisitCounting, Long> {

	// userIdf를 기반으로 방문 횟수를 카운트하는 메서드
	int countByUserId(Long userId);

	VisitCounting findByUserAndCenter(User user, FitnessCenter center);

	/*
	 * @Query("SELECT v.center, COUNT(v.center) AS visitCount " +
	 * "FROM visit_counting v " + "WHERE v.user.id = :userId " +
	 * "GROUP BY v.center " + "ORDER BY visitCount DESC") List<Object[]>
	 * findTop3VisitedCenters(@Param("userId") Long userId);
	 */

}
