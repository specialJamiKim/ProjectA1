package com.projectA1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectA1.model.User;
import com.projectA1.service.UserService;
import com.projectA1.service.VisitCountingService;

import lombok.RequiredArgsConstructor;

//사실 이게 필요한지는 생각해 봐야 할 부분
@RestController
@RequestMapping("/m_visit/")
@RequiredArgsConstructor
public class M_VisitCountingController {
	
	private final VisitCountingService visitCountingService;
	private final UserService userService;
	
	
}
