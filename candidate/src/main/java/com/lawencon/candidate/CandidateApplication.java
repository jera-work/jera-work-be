package com.lawencon.candidate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CandidateApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(CandidateApplication.class, args);
	}

}
