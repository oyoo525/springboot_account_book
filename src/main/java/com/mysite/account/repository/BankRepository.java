package com.mysite.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mysite.account.domain.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {

	
	
	
	
}
