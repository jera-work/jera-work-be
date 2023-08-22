package com.lawencon.candidate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.customskill.InsertCustomSkillReqDto;
import com.lawencon.candidate.service.CustomSkillService;

@RestController
@RequestMapping("/custom-skills")
public class CustomSkillController {

	@Autowired
	private CustomSkillService customSkillService;
	
	@PostMapping
	public ResponseEntity<InsertResDto> insertSkill(@RequestBody InsertCustomSkillReqDto data) {
		final InsertResDto response = customSkillService.createSkill(data);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
