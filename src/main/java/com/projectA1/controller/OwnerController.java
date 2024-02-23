package com.projectA1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projectA1.model.FitnessCenter;
import com.projectA1.model.Owner;
import com.projectA1.service.FitnessCenterService;
import com.projectA1.service.OwnerService;

@Controller
@RequestMapping("/owner/*")
public class OwnerController {

	//오너 추가
	//오너 마이페이지 => 정보수정, 회원탈퇴
	
	@Autowired
	private OwnerService ownerService;
	
	//오너 추가폼 변경완료
	@GetMapping("join")
	public String join() {
		return "/owner/join";
	}
	//오너 정보추가 => 추가 후, 로그인 페이지
	@PostMapping("join")
	public String join(Owner owner) {
		ownerService.join(owner);
		return "/"; // 페이지 수정 필요
	}
	
	//오너 마이페이지(상세보기)
	@GetMapping("view/{id}")
	public String view(@PathVariable long id, Model model) {
		model.addAttribute("owner",ownerService.view(id));
		return "/owner/view";
	}
	
	//오너 정보수정폼
	@GetMapping("update/{id}")
	public String update(@PathVariable long id  ,Model model) {
		model.addAttribute("owner", ownerService.view(id));
		return "/owner/update";
	}
	
	//오너 정보수정
	@PostMapping("update")
	public String update(FitnessCenter fCenter) {
		ownerService.update(fCenter);
		return "/owner/view/"+ fCenter.getCenterNumber();
		}
	
	//오너 회원탈퇴
	@GetMapping("delete/{id}")
	public String delete(@PathVariable long id) {
		FitnessCenterService.delete(id);
		return "good";
	}
}

