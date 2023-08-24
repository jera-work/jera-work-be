package com.lawencon.candidate.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.UpdateResDto;
import com.lawencon.candidate.dto.candidate.CandidatePasswordUpdateReqDto;
import com.lawencon.candidate.dto.profile.CandidateProfileResDto;
import com.lawencon.candidate.dto.profile.CandidateProfileUpdateReqDto;
import com.lawencon.candidate.dto.register.RegisterReqDto;
import com.lawencon.candidate.service.CandidateProfileService;
import com.lawencon.candidate.service.CandidateService;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

	@Autowired
	private CandidateService cdtService;
	@Autowired
	private CandidateProfileService profileService;

	@PostMapping("/register")
	public ResponseEntity<InsertResDto> register(@RequestBody RegisterReqDto data) {
		final InsertResDto response = cdtService.register(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/profile")
	public ResponseEntity<UpdateResDto> updateCandidateProfile(@RequestBody CandidateProfileUpdateReqDto data) {
		final UpdateResDto response = cdtService.updateCandidateProfile(data);
		return new ResponseEntity<UpdateResDto>(response, HttpStatus.OK);
	}
	
	@PatchMapping
	public ResponseEntity<UpdateResDto> changePass(@Valid @RequestBody CandidatePasswordUpdateReqDto data){
		final UpdateResDto response = cdtService.changePassword(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<CandidateProfileResDto> getProfile(){
		final CandidateProfileResDto response = profileService.getProfile();
		return new ResponseEntity<CandidateProfileResDto>(response, HttpStatus.OK);
	}

}
