package com.lawencon.candidate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.register.RegisterReqDto;
import com.lawencon.candidate.service.CandidateService;

@RestController
@RequestMapping("candidates")
public class CandidateController {

	@Autowired
	private CandidateService cdtService;
	
	@PostMapping("/register")
	public ResponseEntity<InsertResDto> register(@RequestBody RegisterReqDto data){
		final InsertResDto response = cdtService.register(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
}
