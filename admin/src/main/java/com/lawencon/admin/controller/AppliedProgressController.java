package com.lawencon.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.appliedprogress.AppliedProgressResDto;
import com.lawencon.admin.service.AppliedProgressService;

@RestController
@RequestMapping("/applied-progress")
public class AppliedProgressController {
	@Autowired
	private AppliedProgressService appliedProgressService;
	
	@GetMapping
	public ResponseEntity<List<AppliedProgressResDto>> getAll(){
		final List<AppliedProgressResDto> responses = appliedProgressService.getAll();
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
	
	@GetMapping("/code")
	public ResponseEntity<AppliedProgressResDto> getByCode(String code){
		final AppliedProgressResDto responses = appliedProgressService.getByCode(code);
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
}
