package com.mysite.account.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.mysite.account.domain.Account;
import com.mysite.account.domain.Member;
import com.mysite.account.repository.AccountRepository;
import com.mysite.account.repository.MemberRepository;
import com.mysite.account.util.JwtUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Value("${jwt.secret}")
	private String secretKey;
	
	private Long expiredMs = 1000 * 60 * 60L;
	

	
	// 회원가입
	public Member join(String id, String pass) {
		Member m = new Member(id, pass, null, null);
		return memberRepository.save(m);
	}
	
	// 로그인
	public String login(String id, String pass) {
		log.info("MemberService : login({}, {})", id, pass);
		log.info("MemberService : SecretKey : {}", secretKey);
		
		Optional<Member> m = memberRepository.findById(id);
		if(m != null && m.get().getPass().equals(pass)) {
			return JwtUtil.createJwt(id, secretKey, expiredMs);
		} else {	
			return null;
		}
	}
	
	// 회원정보 가져오기
	public Optional<Member> getMember(String id) {
		return memberRepository.findById(id);
	}
	
	// 수입 카테고리 추가하기
	@Transactional
	public void addIncomeCategory(String id, String categories) {
	    Optional<Member> memberOptional = memberRepository.findById(id);
	    if (memberOptional.isPresent()) {
	        Member member = memberOptional.get();
	        List<String> incomeCategories = member.getIncomeCategory();
	        if (incomeCategories == null) {
	            incomeCategories = new ArrayList<>();
	        }
	        incomeCategories.add(categories);
	        member.setIncomeCategory(incomeCategories);
	        memberRepository.save(member);
	    }
	}
	
	// 수입 카테고리 수정하기
	@Transactional
	public void modifyIncomeCategory(String id, String categories, int index) {
		Optional<Member> memberOptional = memberRepository.findById(id);
		if(memberOptional.isPresent()) {
			Member member = memberOptional.get();
			List<String> incomeCategories = member.getIncomeCategory();
			incomeCategories.set(index, categories);
			member.setIncomeCategory(incomeCategories);
			memberRepository.save(member);
		}
	}

	// 수입 카테고리 삭제하기
	@Transactional
	public boolean deleteIncomeCategory(String id, String categories, int index) {
		Optional<Member> memberOptional = memberRepository.findById(id);
		if(memberOptional.isPresent()) {
			Member member = memberOptional.get();
			List<String> incomeCategories = member.getIncomeCategory();
			incomeCategories.remove(index);
			member.setIncomeCategory(incomeCategories);
			memberRepository.save(member);
			return true;
		} else {
			return false;			
		}
	}
	
	// 지출 카테고리 추가하기
	@Transactional
	public void addExpenseCategory(String id, String categories) {
	    Optional<Member> memberOptional = memberRepository.findById(id);
	    if (memberOptional.isPresent()) {
	        Member member = memberOptional.get();
	        List<String> expenseCategories = member.getExpenseCategory();
	        if (expenseCategories == null) {
	        	expenseCategories = new ArrayList<>();
	        }
	        expenseCategories.add(categories);
	        member.setExpenseCategory(expenseCategories);
	        memberRepository.save(member);
	    }
	}
	
	// 지출 카테고리 수정하기
	@Transactional
	public void modifyExpenseCategory(String id, String categories, int index) {
		Optional<Member> memberOptional = memberRepository.findById(id);
		if(memberOptional.isPresent()) {
			Member member = memberOptional.get();
			List<String> expenseCategories = member.getExpenseCategory();
			expenseCategories.set(index, categories);
			member.setExpenseCategory(expenseCategories);
			memberRepository.save(member);
		}
	}
	
	// 지출 카테고리 삭제하기
	@Transactional
	public boolean deleteExpenseCategory(String id, String categories, int index) {
		Optional<Member> memberOptional = memberRepository.findById(id);
		if(memberOptional.isPresent()) {
			Member member = memberOptional.get();
			List<String> expenseCategories = member.getExpenseCategory();
			expenseCategories.remove(index);
			member.setExpenseCategory(expenseCategories);
			memberRepository.save(member);
			return true;
		} else {
			return false;			
		}
	}
	
	
	
	
}
