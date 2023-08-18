package com.lawencon.candidate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.savedjob.InsertSavedJobReqDto;
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
}
