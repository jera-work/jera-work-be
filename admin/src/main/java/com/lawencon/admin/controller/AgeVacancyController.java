package com.lawencon.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.agevacancy.AgeVacancyResDto;
import com.lawencon.admin.service.AgeVacancyService;

@RestController
@RequestMapping("/age-vacancies")
public class AgeVacancyController {

	@Autowired
	private AgeVacancyService ageVacancyService;
	
	@GetMapping
	public ResponseEntity<List<AgeVacancyResDto>> getAll(){
		final List<AgeVacancyResDto> responses = ageVacancyService.getAll();
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
}
