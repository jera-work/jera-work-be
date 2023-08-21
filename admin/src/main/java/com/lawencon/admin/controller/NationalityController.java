package com.lawencon.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.nationality.NationalityResDto;
import com.lawencon.admin.service.NationalityService;

@RestController
@RequestMapping("/nationalities")
public class NationalityController {

	@Autowired
	private NationalityService nationalityService;
	
	@GetMapping
	public ResponseEntity<List<NationalityResDto>> getAll(){
		final List<NationalityResDto> responses = nationalityService.getAll();
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
}
