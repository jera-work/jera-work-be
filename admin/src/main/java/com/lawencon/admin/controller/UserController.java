package com.lawencon.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.register.RegisterReqDto;
import com.lawencon.admin.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<InsertResDto> signUp(@Valid @RequestBody RegisterReqDto data) {
		final InsertResDto response = userService.register(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
