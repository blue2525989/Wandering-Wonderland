package com.wanderingwonderland.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.BasicAWSCredentials;

@Configuration
public class SecurityConfig {
		  
	
	    @Bean
		public BasicAWSCredentials basicAWSCredentials() {
			return new BasicAWSCredentials("", "");
		}
}
