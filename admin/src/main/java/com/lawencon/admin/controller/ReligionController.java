package com.lawencon.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.ReligionResDto;
import com.lawencon.admin.service.ReligionService;

@RestController
@RequestMapping("/religions")
public class ReligionController {

	@Autowired
	private ReligionService religionService;
	
	@GetMapping
	public ResponseEntity<List<ReligionResDto>> getAll(){
		final List<ReligionResDto> responses = religionService.getAll();
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
}
