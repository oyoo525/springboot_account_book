package com.mysite.account.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysite.account.domain.Account;
import com.mysite.account.repository.AccountRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	public LocalDate today = LocalDate.now();
	public int year = today.getYear();
	public int month = today.getMonthValue();
	public int day = today.getDayOfMonth();
	
	@Transactional
	public int insertAccount(Account account) {
		log.info("AccountService - insertAccount()");
		Account ac = accountRepository.save(account);
		System.out.println(ac);
		return ac != null ? 1 : 0; 
	}
	
	public int selectIncome(String id) {
		Integer i = accountRepository.sumIncome(id);
		System.out.println(year + "-" + month + "-" + day);
		return i != null ? i : 0;
	}
	
	public int selectExpense(String id) {
		Integer i = accountRepository.sumExpense(id);
		return i != null ? i : 0;
	}
	
	
	
}
