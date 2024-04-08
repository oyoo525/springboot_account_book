package com.mysite.account.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "springboot_account_book")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int no;
	
	@Column(nullable = false)
	private String id;
	
	@Column(columnDefinition = "DATE", nullable = false)
	private LocalDate date;
	 
	@Column(nullable = false)
	private Type type;			// 수입, 지출
	public enum Type {
		income, expense
	};
	
	@Column(nullable = false)
	private CashType type2;		// 현금, 통장, 카드
	public enum CashType {
		account, card, cash
	}
	
	@Column(nullable = true)
	private String bankName;	// 자산 거래처
	
	@Column(nullable = false)
	private String category;	// 계정과목
	
	@Column(nullable = true)
	private String account;		// 거래처(입금 혹은 지출)
	
	@Column(nullable = false)
	private int amount;			// 금액
	
	@Column(columnDefinition = "TEXT")
	private String memo;		// 메모

	@Override
	public String toString() {
		return "id : " + id + ", type : " + type + ", category : " + category
				+ ", account : " + account + ", amount : " + amount + ", memo : " + memo;
	}
}
