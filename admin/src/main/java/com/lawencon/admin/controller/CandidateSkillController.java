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
import com.lawencon.admin.dto.candidateskill.CandidateSkillReqDto;
import com.lawencon.admin.dto.candidateskill.CandidateSkillResDto;
import com.lawencon.admin.service.CandidateSkillService;

@RestController
@RequestMapping("/skills")
public class CandidateSkillController {
	
	@Autowired
	private CandidateSkillService skillService;
	
	@PostMapping
	public ResponseEntity<InsertResDto> insertCandidateSkills(@RequestBody List<CandidateSkillReqDto> data) {
		final InsertResDto response = skillService.createSkill(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<CandidateSkillResDto>> getSkills(@RequestParam String email){
		final List<CandidateSkillResDto> responses = skillService.getSkills(email);
		return new ResponseEntity<List<CandidateSkillResDto>>(responses, HttpStatus.CREATED);
	}

}
