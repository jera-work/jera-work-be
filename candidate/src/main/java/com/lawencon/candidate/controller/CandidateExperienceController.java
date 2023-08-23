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
import com.lawencon.candidate.dto.experience.CandidateExperienceReqDto;
import com.lawencon.candidate.dto.experience.CandidateExperienceResDto;
import com.lawencon.candidate.service.CandidateExperienceService;

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
	
	@GetMapping
	public ResponseEntity<List<CandidateExperienceResDto>> getExperiences(){
		final List<CandidateExperienceResDto> responses = expService.getExperiences();
		return new ResponseEntity<List<CandidateExperienceResDto>>(responses, HttpStatus.OK);
	}
}
