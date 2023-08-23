package com.lawencon.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.appliedstatus.AppliedStatusResDto;
import com.lawencon.admin.service.AppliedStatusService;

@RestController
@RequestMapping("/applied-status")
public class AppliedStatusController {
	@Autowired
	private AppliedStatusService appliedStatusService;
	
	@GetMapping
	public ResponseEntity<List<AppliedStatusResDto>> getAll(){
		final List<AppliedStatusResDto> responses = appliedStatusService.getAll();
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
	
	@GetMapping("/code")
	public ResponseEntity<AppliedStatusResDto> getByCode(String code){
		final AppliedStatusResDto responses = appliedStatusService.getByCode(code);
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
}
