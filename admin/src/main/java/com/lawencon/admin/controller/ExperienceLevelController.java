package com.lawencon.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.experiencelevel.ExperienceLevelResDto;
import com.lawencon.admin.service.ExperienceLevelService;

@RestController
@RequestMapping("/experience-levels")
public class ExperienceLevelController {

	@Autowired
	private ExperienceLevelService experienceLevelService;
	
	@GetMapping
	public ResponseEntity<List<ExperienceLevelResDto>> getAll(){
		final List<ExperienceLevelResDto> responses = experienceLevelService.getAll();
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
}
