package com.lawencon.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages ={"com.lawencon"})
@EntityScan(basePackages ={"com.lawencon"})
@SpringBootApplication
public class AdminApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}

}
