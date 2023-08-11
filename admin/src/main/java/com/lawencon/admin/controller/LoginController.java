package com.lawencon.admin.controller;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.lawencon.admin.dto.login.LoginReqDto;
import com.lawencon.admin.dto.login.LoginResDto;
import com.lawencon.admin.dto.token.TokenReqDto;
import com.lawencon.admin.dto.token.TokenResDto;
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
	public ResponseEntity<?> login(@Valid @RequestBody final LoginReqDto data) {
		final Authentication auth = new UsernamePasswordAuthenticationToken(data.getUserEmail(), data.getUserPass());
		authenticationManager.authenticate(auth);

		final User user = userService.login(data);

		final TokenReqDto request = new TokenReqDto();
		request.setId(user.getId());
		final HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<TokenReqDto> dataLogin = new HttpEntity<>(request, headers);
		
		final TokenResDto token = restTemplate.exchange("http://localhost:8082/tokens", HttpMethod.POST, dataLogin, TokenResDto.class).getBody();

		final LoginResDto loginRes = new LoginResDto();
		loginRes.setId(user.getId());
		loginRes.setToken(token.getToken());
		loginRes.setProfileName(user.getProfile().getProfileName());
		loginRes.setRoleCode(user.getRole().getRoleCode());
		loginRes.setPhotoId(user.getProfile().getPhoto().getId());

		return new ResponseEntity<>(loginRes, HttpStatus.OK);
	}

}
