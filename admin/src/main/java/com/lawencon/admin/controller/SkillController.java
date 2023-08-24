package com.lawencon.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.skill.SkillResDto;
import com.lawencon.admin.service.SkillService;

@RestController
@RequestMapping("/master-skills")
public class SkillController {
	@Autowired
	private SkillService skillService;
	
	@GetMapping
	public ResponseEntity<List<SkillResDto>> getAll(){
		final List<SkillResDto> responses = skillService.getAll();
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
}
