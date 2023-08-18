package com.lawencon.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.jobvacancy.InsertJobVacancyReqDto;
import com.lawencon.admin.dto.jobvacancy.JobSearchResDto;
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

	@GetMapping("/search")
	public ResponseEntity<List<JobSearchResDto>> filter(int startIndex, int endIndex, String vacancyTitle, 
			String degreeId, String cityId, String jobTypeId)  {
		final List<JobSearchResDto> responses = jobService.filter(startIndex, endIndex, vacancyTitle, degreeId, cityId, jobTypeId);
		return new ResponseEntity<List<JobSearchResDto>>(responses, HttpStatus.OK);
	}
}
