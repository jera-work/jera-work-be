package com.lawencon.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.DeleteResDto;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.interviewvacancy.DeleteInterviewVacancyReqDto;
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
	
	@PostMapping("/delete")
	public ResponseEntity<DeleteResDto> deleteInterview(@RequestBody DeleteInterviewVacancyReqDto data){
		final DeleteResDto response = interviewService.deleteInterview(data.getId());
		return new ResponseEntity<DeleteResDto>(response, HttpStatus.OK);
	}

}
