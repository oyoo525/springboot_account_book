package com.mysite.account.domain;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

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
	
	@Column(nullable = false)
	private int year;
	
	@Column(nullable = false)
	private int month;
	
	@Column(nullable = false)
	private int date;
	
	@Column(nullable = false)
	private Type type;
	public enum Type {
		income, expense
	};
	
	@Column
	private String category;
	
	@Column
	private String account;
	
	@Column
	private int amount;
	
	@Column(columnDefinition = "TEXT")
	private String memo;
	
	@Column(columnDefinition = "TEXT")
	private String file;
	
	@Override
	public String toString() {
		return "id : " + id + ", type : " + type + ", category : " + category
				+ ", account : " + account + ", amount : " + amount + ", memo : " + memo;
	}
}
