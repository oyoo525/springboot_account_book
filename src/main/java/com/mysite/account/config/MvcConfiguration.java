package com.mysite.account.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcConfiguration implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**/*.html")
			.addResourceLocations("classpath:/templates/");
		registry.addResourceHandler("/css/**/*.css")
			.addResourceLocations("classpath:/static/css/");
		registry.addResourceHandler("/js/**/*.js")
			.addResourceLocations("classpath:/static/js/");
	}
	
}
