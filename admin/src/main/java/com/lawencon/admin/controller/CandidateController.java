package com.lawencon.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.UpdateResDto;
import com.lawencon.admin.dto.candidate.CandidateInsertReqDto;
import com.lawencon.admin.dto.candidateprofile.CandidateProfileUpdateReqDto;
import com.lawencon.admin.dto.candidateskill.CandidateSkillReqDto;
import com.lawencon.admin.dto.document.CandidateDocumentCreateReqDto;
import com.lawencon.admin.dto.education.CandidateEducationCreateReqDto;
import com.lawencon.admin.dto.experience.CandidateExperienceReqDto;
import com.lawencon.admin.service.CandidateService;

@RestController
@RequestMapping("candidates")
public class CandidateController {

	@Autowired
	private CandidateService cdtService;

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
	
	@PostMapping("/documents")
	public ResponseEntity<InsertResDto> insertCandidateDocs(@RequestBody List<CandidateDocumentCreateReqDto> data) {
		final InsertResDto response = cdtService.insertCandidateDocs(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/educations")
	public ResponseEntity<InsertResDto> insertCandidateEducations(@RequestBody List<CandidateEducationCreateReqDto> data) {
		final InsertResDto response = cdtService.insertCandidateEducations(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/experiences")
	public ResponseEntity<InsertResDto> insertCandidateExperiences(@RequestBody List<CandidateExperienceReqDto> data) {
		final InsertResDto response = cdtService.createExperience(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/skills")
	public ResponseEntity<InsertResDto> insertCandidateSkills(@RequestBody List<CandidateSkillReqDto> data) {
		final InsertResDto response = cdtService.createSkill(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
