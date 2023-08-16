package com.lawencon.candidate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.UpdateResDto;
import com.lawencon.candidate.dto.appliedvacancy.InsertAppliedVacancyReqDto;
import com.lawencon.candidate.dto.appliedvacancy.UpdateProgressReqDto;
import com.lawencon.candidate.service.AppliedVacancyService;

@RestController
@RequestMapping("/applied")
public class AppliedVacancyController {

	@Autowired
	private AppliedVacancyService appliedVacancyService;

	@PostMapping("/apply")
	public ResponseEntity<InsertResDto> apply(@RequestBody InsertAppliedVacancyReqDto data) {
		final InsertResDto response = appliedVacancyService.insertAppliedVacancy(data);
		return new ResponseEntity<InsertResDto>(response, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<UpdateResDto> changeStatus(@RequestBody UpdateProgressReqDto data) {
		final UpdateResDto response = appliedVacancyService.changeAppliedStatusProgress(data);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
