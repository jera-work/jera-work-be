package com.lawencon.candidate.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.lawencon.candidate.filter.AuthorizationFilter;
import com.lawencon.candidate.service.CandidateService;

@Configuration
public class SecurityConfig {

	@Bean
	public AuthenticationManager authManager(HttpSecurity http, CandidateService userService, BCryptPasswordEncoder encoder)
			throws Exception {

		return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userService)
				.passwordEncoder(encoder).and().build();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthorizationFilter authorizationFilter)
			throws Exception {

		http.cors();
		http.csrf().disable();

		http.addFilterAt(authorizationFilter, BasicAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public List<RequestMatcher> matchers() {
		final List<RequestMatcher> matchers = new ArrayList<>();
		matchers.add(new AntPathRequestMatcher("/login", HttpMethod.POST.toString()));
		matchers.add(new AntPathRequestMatcher("/files/**", HttpMethod.GET.toString()));
		matchers.add(new AntPathRequestMatcher("/candidates/register", HttpMethod.POST.toString()));
		return matchers;
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> matchers().forEach(r -> {
			web.ignoring().requestMatchers(r);
		});
	}

//	@Bean
//	public WebMvcConfigurer mvcConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**")
//				.allowedOrigins("http://localhost:4200")
//				.allowedMethods(HttpMethod.GET.name(),
//						HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.PATCH.name(),
//						HttpMethod.DELETE.name());
////				
////				registry.addMapping("/**")
////				.allowedOrigins("http://localhost:57111")
////				.allowedMethods(HttpMethod.GET.name(),
////						HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.PATCH.name(),
////						HttpMethod.DELETE.name());
//			}
//		};
//	}
}
