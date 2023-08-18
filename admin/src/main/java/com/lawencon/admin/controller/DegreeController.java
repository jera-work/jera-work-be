package com.lawencon.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.degree.DegreeResDto;
import com.lawencon.admin.service.DegreeService;

@RestController
@RequestMapping("/degrees")
public class DegreeController {

	@Autowired
	private DegreeService degreeService;
	
	@GetMapping
	public ResponseEntity<List<DegreeResDto>> getAll(){
		final List<DegreeResDto> responses = degreeService.getAll();
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
}
