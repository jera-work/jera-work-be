package com.lawencon.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.city.CityResDto;
import com.lawencon.admin.service.CityService;

@RestController
@RequestMapping("/cities")
public class CityController {

	@Autowired
	private CityService cityService;
	
	@GetMapping
	public ResponseEntity<List<CityResDto>> getAll(){
		final List<CityResDto> responses = cityService.getAll();
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
}
