package com.lawencon.candidate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.UpdateResDto;
import com.lawencon.candidate.dto.appliedvacancy.AppliedVacancyProgressResDto;
import com.lawencon.candidate.dto.appliedvacancy.AppliedVacancyResDto;
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
	
	@GetMapping("/my-applied/page")
	public ResponseEntity<List<AppliedVacancyResDto>> getMyAppliedJobWithLimit(int startIndex, int endIndex) {
		final List<AppliedVacancyResDto> responses = appliedVacancyService.getByCandidateIdWithLimit(startIndex, endIndex);
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
	
	@GetMapping("/my-applied/code")
	public ResponseEntity<AppliedVacancyProgressResDto> getProgress(String appliedId) {
		final AppliedVacancyProgressResDto response = appliedVacancyService.getProgressCode(appliedId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/my-applied")
	public ResponseEntity<List<AppliedVacancyResDto>> getMyAppliedJob() {
		final List<AppliedVacancyResDto> responses = appliedVacancyService.getByCandidateId();
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
}
