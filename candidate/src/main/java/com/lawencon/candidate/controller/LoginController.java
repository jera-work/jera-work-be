package com.lawencon.candidate.controller;

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
import com.lawencon.candidate.dto.token.TokenReqDto;
import com.lawencon.candidate.login.LoginReqDto;
import com.lawencon.candidate.login.LoginResDto;
import com.lawencon.candidate.model.Candidate;
import com.lawencon.candidate.service.CandidateService;

@RestController
@RequestMapping("login")
public class LoginController {

	@Autowired
	private CandidateService cdtService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private RestTemplate restTemplate;

	@PostMapping
	public ResponseEntity<LoginResDto> login(@Valid @RequestBody LoginReqDto data) throws JsonProcessingException {
		System.out.println(data.getUserEmail());
		final Authentication auth = new UsernamePasswordAuthenticationToken(data.getUserEmail(), data.getUserPass());
		authenticationManager.authenticate(auth);

		final Candidate user = cdtService.login(data);

		final TokenReqDto request = new TokenReqDto();
		request.setId(user.getId());
		
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		final RequestEntity<TokenReqDto> token = RequestEntity.post("http://localhost:8082/token").headers(headers).body(request);
		
		final ResponseEntity<String> response = restTemplate.exchange(token, String.class);

		final LoginResDto loginRes = new LoginResDto();
		loginRes.setId(user.getId());
		loginRes.setToken(response.getBody());
		loginRes.setProfileName(user.getCandidateProfile().getProfileName());
		if(user.getCandidateProfile().getPhoto() != null) {
			loginRes.setPhotoId(user.getCandidateProfile().getPhoto().getId());
		}

		return new ResponseEntity<>(loginRes, HttpStatus.OK);
	}

}
