package com.lawencon.tokenauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TokenAuthApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TokenAuthApplication.class, args);
	}

}
