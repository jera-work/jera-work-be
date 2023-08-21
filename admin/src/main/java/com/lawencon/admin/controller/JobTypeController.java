package com.lawencon.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.jobtype.JobTypeResDto;
import com.lawencon.admin.service.JobTypeService;

@RestController
@RequestMapping("/job-types")
public class JobTypeController {

	@Autowired
	private JobTypeService jobTypeService;
	
	@GetMapping
	public ResponseEntity<List<JobTypeResDto>> getAll(){
		final List<JobTypeResDto> responses = jobTypeService.getAll();
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
}
