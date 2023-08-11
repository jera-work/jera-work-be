package com.lawencon.tokenmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.tokenmanager.dto.TokenReqDto;
import com.lawencon.tokenmanager.dto.TokenResDto;
import com.lawencon.tokenmanager.service.TokenService;

@RestController
@RequestMapping("tokens")
public class TokenController {
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenResDto> getToken(@RequestBody TokenReqDto data){
		final TokenResDto response = tokenService.generateToken(data);
		return new ResponseEntity<TokenResDto>(response, HttpStatus.CREATED);
	}

}
