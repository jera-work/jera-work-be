package com.lawencon.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.UpdateResDto;
import com.lawencon.admin.dto.candidate.CandidateInsertReqDto;
import com.lawencon.admin.dto.candidateprofile.CandidateProfileResDto;
import com.lawencon.admin.dto.candidateprofile.CandidateProfileUpdateReqDto;
import com.lawencon.admin.service.CandidateProfileService;
import com.lawencon.admin.service.CandidateService;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

	@Autowired
	private CandidateService cdtService;
	@Autowired
	private CandidateProfileService profileService;

	@PostMapping("/register")
	public ResponseEntity<InsertResDto> register(@RequestBody CandidateInsertReqDto data) {
		final InsertResDto response = cdtService.insertCandidate(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/profile")
	public ResponseEntity<UpdateResDto> updateCandidateProfile(@RequestBody CandidateProfileUpdateReqDto data) {
		final UpdateResDto response = cdtService.updateCandidateProfile(data);
		return new ResponseEntity<UpdateResDto>(response, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<CandidateProfileResDto> getProfile(@RequestParam String email){
		final CandidateProfileResDto response = profileService.getProfile(email);
		return new ResponseEntity<CandidateProfileResDto>(response, HttpStatus.OK);
	}
}
