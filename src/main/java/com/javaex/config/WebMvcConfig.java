package com.javaex.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/jblog/**")  // 경로
		.allowedMethods("GET","POST","DELETE","PUT")
		.allowedOrigins("http://localhost:8080")
		.allowedHeaders("*")
		.exposedHeaders("Authorization")
		.allowCredentials(true);
	}
	
}
