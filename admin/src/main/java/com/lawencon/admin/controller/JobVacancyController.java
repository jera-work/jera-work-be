package com.lawencon.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.jobvacancy.InsertJobVacancyReqDto;
import com.lawencon.admin.service.JobVacancyService;

@RestController
@RequestMapping("/jobs")
public class JobVacancyController {

	@Autowired
	private JobVacancyService jobService;

	@PostMapping
	public ResponseEntity<InsertResDto> insertJob(@RequestBody InsertJobVacancyReqDto data) {
		final InsertResDto response = jobService.insertJob(data);
		return new ResponseEntity<InsertResDto>(response, HttpStatus.CREATED);
	}

}
