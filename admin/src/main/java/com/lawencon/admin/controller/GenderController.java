package com.lawencon.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.gender.GenderResDto;
import com.lawencon.admin.service.GenderService;

@RestController
@RequestMapping("/genders")
public class GenderController {

	@Autowired
	private GenderService genderService;
	
	@GetMapping
	public ResponseEntity<List<GenderResDto>> getGenders() {
		final List<GenderResDto> responses = genderService.getGenders();
		return new ResponseEntity<List<GenderResDto>>(responses, HttpStatus.OK);
	}
	
}
