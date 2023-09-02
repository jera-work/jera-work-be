package com.lawencon.admin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.UpdateResDto;
import com.lawencon.admin.dto.profile.ProfileUpdateReqDto;
import com.lawencon.admin.dto.user.UserChangePasswordReqDto;
import com.lawencon.admin.dto.user.UserCreateReqDto;
import com.lawencon.admin.dto.user.UserProfileResDto;
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
	public ResponseEntity<List<UserResDto>> getUsers(@RequestParam String roleCode) {
		final List<UserResDto> response = userService.getUsers(roleCode);
		return new ResponseEntity<List<UserResDto>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<UserResDto>> getAllUsers() {
		final List<UserResDto> response = userService.getAllUsers();
		return new ResponseEntity<List<UserResDto>>(response, HttpStatus.OK);
	}
	
	@PatchMapping("/change-password")
	public ResponseEntity<UpdateResDto> changePass(@Valid @RequestBody UserChangePasswordReqDto data){
		final UpdateResDto response = userService.changePassword(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/profile")
	public ResponseEntity<UserProfileResDto> getProfile(){
		final UserProfileResDto response = userService.getProfile();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PatchMapping("/profile")
	public ResponseEntity<UpdateResDto> updateProfile(@RequestBody ProfileUpdateReqDto data){
		final UpdateResDto response = userService.updateProfile(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/report")
	public ResponseEntity<InsertResDto> getReport(){
		final InsertResDto response = userService.getReport();
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/company")
	public ResponseEntity<List<UserResDto>> getUserByCompany() {
		final List<UserResDto> response = userService.getByCompany();
		return new ResponseEntity<List<UserResDto>>(response, HttpStatus.OK);
	}

}
