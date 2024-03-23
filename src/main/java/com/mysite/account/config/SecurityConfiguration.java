package com.mysite.account.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mysite.account.service.MemberService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private MemberService memberService;
	
	@Value("${jwt.secret}")
	private String secretKey;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.httpBasic().disable()
				.csrf().disable()
				.cors().disable()
				.authorizeRequests()
				.requestMatchers("/", "/join").permitAll()
				.requestMatchers("/move").authenticated()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilterBefore(new JwtFilter(memberService, secretKey), UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/ignore1", "/ignore2");
	}
	
	
	
}
