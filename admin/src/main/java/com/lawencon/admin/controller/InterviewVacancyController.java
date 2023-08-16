package com.lawencon.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.interviewvacancy.InsertInterviewVacancyReqDto;
import com.lawencon.admin.service.InterviewVacancyService;

@RestController
@RequestMapping("/interviews")
public class InterviewVacancyController {
	
	@Autowired
	private InterviewVacancyService interviewService;
	
	@PostMapping
	public ResponseEntity<InsertResDto> insertInterview(@RequestBody InsertInterviewVacancyReqDto data) {
		final InsertResDto response = interviewService.insertInterview(data);
		return new ResponseEntity<InsertResDto>(response, HttpStatus.CREATED);
	}

}
