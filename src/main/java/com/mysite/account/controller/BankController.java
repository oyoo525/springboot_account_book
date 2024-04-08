package com.mysite.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.mysite.account.service.BankService;

@Controller
public class BankController {

	@Autowired
	private BankService bankService;
	
	@GetMapping("/bank")
	public String bankPage() {
		return "views/bank";
	}
	
	
}
