package com.mysite.account.util;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtUtil {
	// 토큰 생성
	public static String createJwt(String userName, String secretKey, Long expiredMs) {
		Claims claims = Jwts.claims();
		claims.put("userName", userName);
		
		 String jwt = Jwts.builder()
					.setClaims(claims)
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + expiredMs))
					.signWith(SignatureAlgorithm.HS256, secretKey)
					.compact();
		 log.info("JwtUtil : createJwt() - claims : {}", claims);
		 log.info("JwtUtil : createJwt() - jwt : {}", jwt);
		 
		 return jwt;
	}
	
	// 유저이름 가져오기
	public static String getUserName(String token, String secretKey) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
				.getBody().get("userName", String.class);
	}
	
	// 만료여부
	public static boolean isExpired(String token, String secretKey) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
				.getBody().getExpiration().before(new Date());
	}
}
