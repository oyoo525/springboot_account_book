package com.mysite.account.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "springboot_account_bank")
public class Bank {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int no;
	
	@Column(nullable = false)
	private String id;
	
	@Column(nullable = false)
	private Type type; 
	public enum Type {
		account, card, deposit, saving, loan
	}
	
	@Column(nullable = false)
	private String name;				// 통장명, 카드명 등
	
	@Column(nullable = true)
	private LocalDate paymentDate;		// 납부일자
	
	@Column(nullable = true)
	private LocalDate matureityDate;	// 만기일자
	
}
