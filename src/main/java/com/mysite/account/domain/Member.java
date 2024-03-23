package com.mysite.account.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "springboot_account_member")
public class Member {

	@Id
	@Column(unique = true, nullable = false)
	private String id;
	
	@Column(nullable = false)
	private String pass;
	
	@Column(nullable = true)
	private List<String> IncomeCategory;
	
	@Column(nullable = true)
	private List<String> ExpenseCategory;
	
	
	
	
}
