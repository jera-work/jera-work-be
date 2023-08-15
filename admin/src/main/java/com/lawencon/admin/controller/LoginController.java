package com.lawencon.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lawencon.admin.dto.login.LoginReqDto;
import com.lawencon.admin.dto.login.LoginResDto;
import com.lawencon.admin.dto.token.TokenReqDto;
import com.lawencon.admin.model.User;
import com.lawencon.admin.service.UserService;

@RestController
@RequestMapping("login")
public class LoginController {

	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private RestTemplate restTemplate;

	@PostMapping
	public ResponseEntity<LoginResDto> login(@Valid @RequestBody LoginReqDto data) throws JsonProcessingException {
		System.out.println(data.getUserEmail());
		final Authentication auth = new UsernamePasswordAuthenticationToken(data.getUserEmail(), data.getUserPass());
		authenticationManager.authenticate(auth);

		final User user = userService.login(data);

		final String tokenURL = "http://localhost:8082/token";
		
		final TokenReqDto request = new TokenReqDto();
		request.setId(user.getId());
		
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		final RequestEntity<TokenReqDto> token = RequestEntity.post(tokenURL).headers(headers).body(request);
		
		final ResponseEntity<String> response = restTemplate.exchange(token, String.class);

		final LoginResDto loginRes = new LoginResDto();
		loginRes.setId(user.getId());
		loginRes.setToken(response.getBody());
		loginRes.setProfileName(user.getProfile().getProfileName());
		loginRes.setRoleCode(user.getRole().getRoleCode());
		if(loginRes.getPhotoId() != null) {
			loginRes.setPhotoId(user.getProfile().getPhoto().getId());
		}
		loginRes.setCompanyId(user.getProfile().getCompany().getId());

		return new ResponseEntity<>(loginRes, HttpStatus.OK);
	}

}
