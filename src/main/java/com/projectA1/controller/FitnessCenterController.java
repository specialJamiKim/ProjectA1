package com.projectA1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projectA1.model.FitnessCenter;

@Controller
@RequestMapping("/fitnessCenter/*")
public class FitnessCenterController {

	
	//추가폼
	@GetMapping("insert")
	public String join(FitnessCenter f) {
		return null;
	}
	//추가
	
	//수정폼
	
	//수정
	
	//삭제
	
	//상세보기
	
	//전체보기
}
