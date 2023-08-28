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
import com.lawencon.admin.dto.question.InsertQuestionReqDto;
import com.lawencon.admin.dto.question.QuestionResDto;
import com.lawencon.admin.service.QuestionService;

@RestController
@RequestMapping("/questions")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@PostMapping
	public ResponseEntity<InsertResDto> createQuestion(@RequestBody List<InsertQuestionReqDto> data) {
		final InsertResDto response = questionService.createQuestion(data);
		return new ResponseEntity<InsertResDto>(response, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<QuestionResDto>> getQuestions(String jobId) {
		final List<QuestionResDto> responses = questionService.getQuestions(jobId);
		return new ResponseEntity<List<QuestionResDto>>(responses, HttpStatus.OK);
	}
}
