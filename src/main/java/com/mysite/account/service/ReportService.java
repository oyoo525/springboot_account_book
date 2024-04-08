package com.mysite.account.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mysite.account.domain.Report;
import com.mysite.account.repository.AccountRepository;

public class ReportService {

	@Autowired
	private AccountRepository accountRepository;
	
	public Map<String, Integer> dayReport(String id, LocalDate periodFrom, LocalDate periodTo) {
		
		
		
		
		return null;
	}
	
	
	
	
	
	
}
