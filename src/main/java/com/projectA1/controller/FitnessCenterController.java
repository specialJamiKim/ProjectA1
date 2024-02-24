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

@Controller
@RequestMapping("/fitnessCenter/*")
public class FitnessCenterController {
	
	@Autowired
	private FitnessCenterService FitnessCenterService;

	//추가폼
	@GetMapping("join")
    public String join() {
        return "/fitnesscenter/join";
    }
	
	// 피트니스 센터 추가
    @PostMapping("join")
    public String join(FitnessCenter fitnessCenter) {
    	FitnessCenterService.join(fitnessCenter);
        return "/fitnesscenter/login";
    }

	//수정폼
    @GetMapping("update/{username}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("fitnessCenter", FitnessCenterService.view(id));
        return "/fitnesscenter/update";
    }
	        
	//수정
    @PostMapping("update")
    public String updateFitnessCenter(FitnessCenter fitnessCenter) {
    	FitnessCenterService.update(fitnessCenter);
        return "redirect:/fitnesscenter";
    }
	
	//삭제
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
    	FitnessCenterService.deleteFitnessCenter(id);
        return "success";
    }
	
	// 피트니스 센터 상세보기
    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("fitnessCenter", FitnessCenterService.view(id));
        return "/fitnesscenter/view";
    }
    
	//전체보기
    @GetMapping("/fitnessCenter/*")
    public String getAllFitnessCenters(Model model) {
        model.addAttribute("fitnessCenters", FitnessCenterService.viewAll());
        return "/fitnesscenter";
    }
}
