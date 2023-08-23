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
import com.lawencon.admin.dto.education.CandidateEducationCreateReqDto;
import com.lawencon.admin.dto.education.CandidateEducationResDto;
import com.lawencon.admin.service.CandidateEducationService;

@RestController
@RequestMapping("/educations")
public class CandidateEducationController {
	
	@Autowired
	private CandidateEducationService eduService;
	
	@PostMapping
	public ResponseEntity<InsertResDto> insertCandidateEducations(@RequestBody List<CandidateEducationCreateReqDto> data) {
		final InsertResDto response = eduService.insertCandidateEducations(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<CandidateEducationResDto>> getEducations(@RequestParam String email){
		final List<CandidateEducationResDto> responses = eduService.getEducations(email);
		return new ResponseEntity<List<CandidateEducationResDto>>(responses, HttpStatus.OK);
	}

}
