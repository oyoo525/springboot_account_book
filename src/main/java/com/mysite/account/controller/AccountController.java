package com.mysite.account.controller;

import java.time.LocalDate;
import java.util.Optional;

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
import com.mysite.account.domain.Member;
import com.mysite.account.service.AccountService;
import com.mysite.account.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AccountController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private MemberService memberService;
	
	
	@GetMapping("/")
	public String index(Model model, HttpSession session) {
		String id = (String)session.getAttribute("loginId");
		model.addAttribute("loginId", id);
		
		LocalDate today = LocalDate.now();
		int year = today.getYear();
		int month = today.getMonthValue();
		int date = today.getDayOfMonth();
		
		model.addAttribute("income", accountService.selectIncome(id));
		model.addAttribute("expense", accountService.selectExpense(id));
				
		return "index";
	}
	

	@GetMapping("/account")
	public String accountPage(HttpSession session, Model model) {
		String id = (String)session.getAttribute("loginId");
		Optional<Member> member = memberService.getMember(id);
		if(member.isPresent()) {
			model.addAttribute("incomeCategory", member.get().getIncomeCategory());
			model.addAttribute("expenseCategory", member.get().getExpenseCategory());
		}
		return "views/account";
	}

	
	@PostMapping("/account")
	@ResponseBody
	public Account account(HttpSession session, @RequestBody Account account) {
		log.info("AccountController - account()");
		String id = (String)session.getAttribute("loginId");
		account.setId(id);
		
		System.out.println(account);
		accountService.insertAccount(account);
		return account;
	}
	
	
	
	
}
