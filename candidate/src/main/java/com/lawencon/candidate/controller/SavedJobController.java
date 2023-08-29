package com.lawencon.candidate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.candidate.dto.DeleteResDto;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.savedjob.InsertSavedJobReqDto;
import com.lawencon.candidate.dto.savedjob.SavedJobResDto;
import com.lawencon.candidate.service.SavedJobService;

@RestController
@RequestMapping("save-jobs")
public class SavedJobController {

	@Autowired
	private SavedJobService savedJobService;

	@PostMapping
	public ResponseEntity<InsertResDto> apply(@RequestBody InsertSavedJobReqDto data) {
		final InsertResDto response = savedJobService.createSavedJob(data);
		return new ResponseEntity<InsertResDto>(response, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<SavedJobResDto> getByJobAndCandidate(String jobId) {
		final SavedJobResDto response = savedJobService.getByCandidateAndJob(jobId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<DeleteResDto> unsaveJobs(String savedId) {
		final DeleteResDto response = savedJobService.deleteSavedJobs(savedId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/my-saved")
	public ResponseEntity<List<SavedJobResDto>> getMySavedJob() {
		final List<SavedJobResDto> responses = savedJobService.getMySavedJob();

		return new ResponseEntity<>(responses, HttpStatus.OK);
	}

	@GetMapping("/my-saved/page")
	public ResponseEntity<List<SavedJobResDto>> getMySavedJobWithLimit(int startIndex, int endIndex) {
		final List<SavedJobResDto> responses = savedJobService.getMySavedJobWithLimit(startIndex, endIndex);

		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
}
