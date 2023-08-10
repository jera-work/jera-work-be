package com.lawencon.candidate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages ={"com.lawencon"})
@EntityScan(basePackages ={"com.lawencon"})
public class CandidateApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(CandidateApplication.class, args);
	}

}
