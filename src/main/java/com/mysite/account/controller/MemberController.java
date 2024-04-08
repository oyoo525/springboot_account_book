package com.mysite.account.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysite.account.domain.Member;
import com.mysite.account.service.EmailService;
import com.mysite.account.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/sendMail")
	@ResponseBody
	public String sendEmail(@RequestParam("id") String id,
			@RequestParam("verify") String verify) {
		String mailContent = "가입을 위해 인증번호 "+ verify + "를 정확히 입력해주세요.";	
		
		emailService.sendEmail(id, "가입 인증번호입니다.", mailContent);
		return "발송완료";
	}
	
	@GetMapping("/join")
	public String joinPage() {
		return "views/join";
	}
	
	@PostMapping("/join")
	@ResponseBody
	public Member join(@RequestParam("id") String id, @RequestParam("pass") String pass) {
		return memberService.join(id, pass);
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "views/login";
	}
	
	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<String> login(HttpSession session,
			@RequestParam("id") String id, @RequestParam("pass") String pass) {
		log.info("MemberController : login({}, {})", id, pass);
		ResponseEntity<String> result = ResponseEntity.ok().body(memberService.login(id, pass));
		
		if(result != null) {
			session.setAttribute("loginId", id);
		}
		
		return result;
	}
	
	
	@PostMapping("/logout")
	@ResponseBody
	public void logout(HttpSession session) {
	    session.invalidate();
	}

	
	@GetMapping("/category")
	public String categoryPage(HttpSession session, Model model) {
		String id = (String)session.getAttribute("loginId");
		Optional<Member> m = memberService.getMember(id);
		
		model.addAttribute("incomeCategory", m.get().getIncomeCategory());
		model.addAttribute("expenseCategory", m.get().getExpenseCategory());
		
		return "views/category";
	}
	
	
	@PostMapping("/addIncomeCategory")
	@ResponseBody
	public String addIncomeCategory(HttpSession session, @RequestParam("category") String category) {
		System.out.println("category : " + category);
		String id = (String)session.getAttribute("loginId");
		memberService.addIncomeCategory(id, category);
		return "추가완료";
	}
	
	@PostMapping("/modifyIncomeCategory")
	@ResponseBody
	public String modifyIncomeCategory(HttpSession session, 
			@RequestParam("category") String category, @RequestParam("index") int index) {
		System.out.println("category : " + category);
		String id = (String)session.getAttribute("loginId");
		memberService.modifyIncomeCategory(id, category, index);
		return "수정완료";
	}
	
	@PostMapping("/deleteIncomeCategory")
	@ResponseBody
	public boolean deleteIncomeCategory(HttpSession session, 
			@RequestParam("category") String category, @RequestParam("index") int index) {
		System.out.println("category : " + category);
		String id = (String)session.getAttribute("loginId");
		return memberService.deleteIncomeCategory(id, category, index);
	}
	
	@PostMapping("/addExpenseCategory")
	@ResponseBody
	public String addExpenseCategory(HttpSession session, @RequestParam("category") String category) {
		System.out.println("category : " + category);
		String id = (String)session.getAttribute("loginId");
		memberService.addExpenseCategory(id, category);
		return "추가완료";
	}
	
	@PostMapping("/modifyExpenseCategory")
	@ResponseBody
	public String modifyExpenseCategory(HttpSession session, 
			@RequestParam("category") String category, @RequestParam("index") int index) {
		System.out.println("category : " + category);
		String id = (String)session.getAttribute("loginId");
		memberService.modifyExpenseCategory(id, category, index);
		return "수정완료";
	}
	
	@PostMapping("/deleteExpenseCategory")
	@ResponseBody
	public boolean deleteExpenseCategory(HttpSession session, 
			@RequestParam("category") String category, @RequestParam("index") int index) {
		System.out.println("category : " + category);
		String id = (String)session.getAttribute("loginId");
		return memberService.deleteExpenseCategory(id, category, index);
	}
	
	
	
	
}
