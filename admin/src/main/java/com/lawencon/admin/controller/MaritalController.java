package com.lawencon.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.marital.MaritalResDto;
import com.lawencon.admin.service.MaritalService;

@RestController
@RequestMapping("/maritals")
public class MaritalController {
	@Autowired
	private MaritalService maritalService;
	
	@GetMapping
	public ResponseEntity<List<MaritalResDto>> getAll(){
		final List<MaritalResDto> responses = maritalService.getAll();
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
}
