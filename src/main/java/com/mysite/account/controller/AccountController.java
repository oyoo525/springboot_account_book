package com.mysite.account.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysite.account.domain.Account;
import com.mysite.account.service.AccountService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@GetMapping("/")
	public String index(Model model, HttpSession session) {
		String id = (String)session.getAttribute("loginId");
		model.addAttribute("loginId", id);
		model.addAttribute("income", accountService.selectIncome(id));
		model.addAttribute("expense", accountService.selectExpense(id));
				
		return "index";
	}
	

	@GetMapping("/account")
	public String accountPage() {
		return "views/account";
	}

	
	@PostMapping("/account")
	@ResponseBody
	public Account account(@RequestBody Account account) {
		log.info("AccountController - account()");
		System.out.println(account);
		accountService.insertAccount(account);
		return account;
	}
	
	
	
	
}
