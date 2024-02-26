package com.projectA1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projectA1.model.FitnessCenter;
import com.projectA1.service.FitnessCenterService;
import com.projectA1.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/fitnessCenter/*")
@RequiredArgsConstructor
public class FitnessCenterController {
	
	private final FitnessCenterService FitnessCenterService;

	//추가폼
	@GetMapping("centerjoin")
    public String join() {
        return "center/centerjoin";
    }
	
	// 피트니스 센터 로그인
    @PostMapping("ownerlogin")
    public String join(FitnessCenter fitnessCenter) {
    	FitnessCenterService.join(fitnessCenter);
        return "redirect:/user/ownerlogin";
    }

	//수정폼
    @GetMapping("update/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("fitnessCenter", FitnessCenterService.view(id));
        return "/center/update";
    }
	        
	//수정
    @PostMapping("update")
    public String updateFitnessCenter(FitnessCenter fitnessCenter) {
    	FitnessCenterService.update(fitnessCenter.getId(),fitnessCenter);
        return "redirect:/user/ownerpage/"+ fitnessCenter.getId();
    }
	
	//삭제
    @GetMapping("delete/{id}")
    public String delete(@PathVariable Long id) {
    	FitnessCenterService.deleteFitnessCenter(id);
        return "redirect:/success";
    }
	
	// 피트니스 센터 상세보기
    @GetMapping("view/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("fitnessCenter", FitnessCenterService.view(id));
        return "/center/gymview";
    }
    
	//전체보기
    @GetMapping("/fitnessCenter")
    public String getAllFitnessCenters(Model model) {
        model.addAttribute("fitnessCenters", FitnessCenterService.viewAll());
        return "/center/gymlist";
    }
}
