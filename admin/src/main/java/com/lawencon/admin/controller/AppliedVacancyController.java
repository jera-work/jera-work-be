package com.lawencon.admin.controller;

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

import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.UpdateResDto;
import com.lawencon.admin.dto.appliedstatus.UpdateStatusReqDto;
import com.lawencon.admin.dto.appliedvacancy.AppliedVacancyAdminResDto;
import com.lawencon.admin.dto.appliedvacancy.AppliedVacancyByProgressAdminResDto;
import com.lawencon.admin.dto.appliedvacancy.AppliedVacancyCandidateDetailResDto;
import com.lawencon.admin.dto.appliedvacancy.AppliedVacancyProgressResDto;
import com.lawencon.admin.dto.appliedvacancy.AppliedVacancyResDto;
import com.lawencon.admin.dto.appliedvacancy.InsertAppliedVacancyReqDto;
import com.lawencon.admin.dto.appliedvacancy.UpdateProgressReqDto;
import com.lawencon.admin.service.AppliedVacancyService;

@RestController
@RequestMapping("/applied")
public class AppliedVacancyController {

	@Autowired
	private AppliedVacancyService appliedVacancyService;
	
	@PutMapping
	public ResponseEntity<UpdateResDto> changeProgress(@RequestBody UpdateProgressReqDto data) {
		final UpdateResDto response = appliedVacancyService.changeAppliedStatusProgress(data);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("/change-status")
	public ResponseEntity<UpdateResDto> changeStatus(@RequestBody UpdateStatusReqDto data) {
		final UpdateResDto response = appliedVacancyService.changeAppliedStatus(data);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/apply")
	public ResponseEntity<InsertResDto> apply(@RequestBody InsertAppliedVacancyReqDto data) {
		final InsertResDto response = appliedVacancyService.insertAppliedVacancy(data);
		return new ResponseEntity<InsertResDto>(response, HttpStatus.CREATED);
	}

	@GetMapping("/my-applied")
	public ResponseEntity<List<AppliedVacancyResDto>> getMyAppliedJob(String email, int startIndex, int endIndex) {
		final List<AppliedVacancyResDto> responses = appliedVacancyService.getByCandidateId(email, startIndex, endIndex);
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
	
	@GetMapping("/my-applied/detail")
	public ResponseEntity<AppliedVacancyProgressResDto> getAppliedByJobAndCandidate(String jobCode, String email) {
		final AppliedVacancyProgressResDto responses = appliedVacancyService.getAppliedByJobAndCandidate(jobCode, email);
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
	
	@GetMapping("/progress")
	public ResponseEntity<List<AppliedVacancyAdminResDto>> getByProgress(String progressId, String jobVacancyId) {
		final List<AppliedVacancyAdminResDto> responses = appliedVacancyService.getByProgress(progressId, jobVacancyId);
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
	
	@GetMapping("/job")
	public ResponseEntity<List<AppliedVacancyAdminResDto>> getByJob(String jobId) {
		final List<AppliedVacancyAdminResDto> responses = appliedVacancyService.getByJobVacancyId(jobId);
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<AppliedVacancyCandidateDetailResDto> getAppliedCandidateDetail(String appliedId){
		final AppliedVacancyCandidateDetailResDto response = appliedVacancyService.getAppliedCandidateDetail(appliedId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/counts")
	public ResponseEntity<List<AppliedVacancyByProgressAdminResDto>> getProgressCount(String jobVacancyId){
		final List<AppliedVacancyByProgressAdminResDto> results = appliedVacancyService.getProgressCount(jobVacancyId);
		return new ResponseEntity<>(results, HttpStatus.OK);
	}
}
