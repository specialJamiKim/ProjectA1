package com.projectA1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projectA1.config.auth.PrincipalUser;
import com.projectA1.model.Owner;
import com.projectA1.service.OwnerService;

@Controller
@RequestMapping("/owner/*")
public class OwnerController {

	//오너 추가
	//오너 마이페이지 => 정보수정, 회원탈퇴
	
	@Autowired
	private OwnerService ownerService;
	
	//오너 마이페이지
	@GetMapping("ownerpage")
	public String ownerPage(@AuthenticationPrincipal PrincipalUser principalUser,Model model) {
	    // 사용자 정보를 통해 해당 사용자가 관리하는 센터의 이름을 조회하여 모델에 추가합니다.
//	    String centerName = ownerService.getCenterNameForAdmin(principal.getName());
//	    model.addAttribute("centerName", centerName);
		return "/owner/ownerpage";
	}
	
	
	//오너 추가폼 변경완료
	@GetMapping("join")
	public String join() {
		return "/owner/join";
	}
	//오너 정보추가 => 추가 후, 로그인 페이지
	@PostMapping("join")
	@ResponseBody
	public String join(@RequestBody Owner owner) {
		List<String> roles = new ArrayList<>();
		roles.add("ROLE_OWNER");
		owner.setRole(roles);
		System.out.println("111");
		ownerService.join(owner);
		return "success"; // 페이지 수정 필요
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
	public String update(long id, Owner owner) {
		ownerService.update(id, owner);
		return "/owner/list";
		}
	
	//오너 회원탈퇴
	@GetMapping("delete/{id}")
	public String delete(@PathVariable Long id) {
		ownerService.delete(id);
		return "good";
	}
}

