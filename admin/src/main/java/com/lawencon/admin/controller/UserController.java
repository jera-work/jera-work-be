package com.lawencon.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.user.UserCreateReqDto;
import com.lawencon.admin.dto.user.UserResDto;
import com.lawencon.admin.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<InsertResDto> createUser(@RequestBody UserCreateReqDto data) {
		final InsertResDto response = userService.createUser(data);
		return new ResponseEntity<InsertResDto>(response, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<UserResDto>> getUsers(@RequestParam String roleCode, @RequestParam String companyId) {
		final List<UserResDto> response = userService.getUsers(roleCode, companyId);
		return new ResponseEntity<List<UserResDto>>(response, HttpStatus.OK);
	}

}
