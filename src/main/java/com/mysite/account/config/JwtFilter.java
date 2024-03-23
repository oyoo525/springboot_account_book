package com.mysite.account.config;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mysite.account.service.MemberService;
import com.mysite.account.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

	private final MemberService memberService;
	private final String secketKey;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
		log.info("authorization : {}", authorization);
		
		// 토큰 꺼내기
		if(authorization == null || !authorization.startsWith("Bearer ")) {
			log.error("authorization을 잘못 보냈습니다.");
			filterChain.doFilter(request, response);
			return ;
		}
		String token = authorization.split(" ")[1];
		
		// 만료여부
		if(JwtUtil.isExpired(token, secketKey)) {
			log.error("Token이 만료되었습니다.");
			filterChain.doFilter(request, response);
			return ;
		}

		// 유저이름
		String userName = JwtUtil.getUserName(token, secketKey);
		log.info("userName : {}", userName);
		
		// 권한부여
		UsernamePasswordAuthenticationToken authenticationToken = 
				new UsernamePasswordAuthenticationToken(userName,  null, List.of(new SimpleGrantedAuthority("USER")));
		
		// 상세내용
		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		filterChain.doFilter(request, response);
	
	
	}
}
