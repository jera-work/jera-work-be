package com.lawencon.candidate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.education.CandidateEducationCreateReqDto;
import com.lawencon.candidate.dto.education.CandidateEducationResDto;
import com.lawencon.candidate.service.CandidateEducationService;

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
	public ResponseEntity<List<CandidateEducationResDto>> getEducations(){
		final List<CandidateEducationResDto> responses = eduService.getEducationsList();
		return new ResponseEntity<List<CandidateEducationResDto>>(responses, HttpStatus.OK);
	}
}
