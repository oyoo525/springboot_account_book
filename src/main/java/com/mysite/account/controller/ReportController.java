package com.mysite.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReportController {

	@GetMapping("/report")
	public String reportPage(HttpSession session) {
		String id = (String)session.getAttribute("loginId");
		
		
		
		
		return "views/report";
	}
	
	
	
}
