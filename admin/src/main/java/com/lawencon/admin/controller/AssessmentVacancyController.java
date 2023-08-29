package com.lawencon.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.DeleteResDto;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.assessmentvacancy.DeleteAssessmentVacancyReqDto;
import com.lawencon.admin.dto.assessmentvacancy.InsertAssessmentVacancyReqDto;
import com.lawencon.admin.service.AssessmentVacancyService;

@RestController
@RequestMapping("/assessments")
public class AssessmentVacancyController {

	@Autowired
	private AssessmentVacancyService assessmentService;

	@PostMapping
	public ResponseEntity<InsertResDto> insertAssessment(@RequestBody InsertAssessmentVacancyReqDto data) {
		final InsertResDto response = assessmentService.insertAssessment(data);
		return new ResponseEntity<InsertResDto>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<DeleteResDto> deleteAssessment(@RequestBody DeleteAssessmentVacancyReqDto data){
		final DeleteResDto response = assessmentService.deleteAssessment(data.getId());
		return new ResponseEntity<DeleteResDto>(response, HttpStatus.OK);
	}

}
