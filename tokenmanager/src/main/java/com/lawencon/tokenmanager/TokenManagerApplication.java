package com.lawencon.tokenmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages ={"com.lawencon"})
@EntityScan(basePackages ={"com.lawencon"})
public class TokenManagerApplication{

	public static void main(String[] args) {
		SpringApplication.run(TokenManagerApplication.class, args);
	}

}
