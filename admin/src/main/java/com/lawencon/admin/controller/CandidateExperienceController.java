package com.lawencon.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.candidateexperience.CandidateExperienceReqDto;
import com.lawencon.admin.service.CandidateExperienceService;

@RestController
@RequestMapping("/experiences")
public class CandidateExperienceController {

	@Autowired
	private CandidateExperienceService expService;
	
	@PostMapping
	public ResponseEntity<InsertResDto> insertCandidateExperiences(@RequestBody List<CandidateExperienceReqDto> data) {
		final InsertResDto response = expService.createExperience(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
