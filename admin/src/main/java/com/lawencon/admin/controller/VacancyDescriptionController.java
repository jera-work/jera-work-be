package com.lawencon.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.vacancydescription.VacancyDescriptionResDto;
import com.lawencon.admin.service.VacancyDescriptionService;

@RestController
@RequestMapping("/vacancy-descriptions")
public class VacancyDescriptionController {
	
	@Autowired
	private VacancyDescriptionService descriptionService;
	
	@GetMapping
	public ResponseEntity<VacancyDescriptionResDto> getById(String descId)  {
		final VacancyDescriptionResDto response = descriptionService.getDetail(descId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
