package com.mysite.account.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mysite.account.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	@Query(value = "SELECT SUM(amount) FROM springboot_account_book WHERE id = :id AND type = 0", nativeQuery = true)
	public Integer sumIncome(@Param("id") String id);
	
	@Query(value = "SELECT SUM(amount) FROM springboot_account_book WHERE id = :id AND type = 1", nativeQuery = true)
	public Integer sumExpense(@Param("id") String id);
	
	
	Optional<List<Account>> findByIdAndCategory(String id, String category);
	
	
	
}
