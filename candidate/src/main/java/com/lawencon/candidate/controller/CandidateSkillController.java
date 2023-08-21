package com.lawencon.candidate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.candidateskill.CandidateSkillReqDto;
import com.lawencon.candidate.service.CandidateSkillService;

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

}
