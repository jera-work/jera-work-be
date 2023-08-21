package com.lawencon.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.questionanswer.InsertQuestionAnswerReqDto;
import com.lawencon.admin.service.QuestionAnswerService;

@RestController
@RequestMapping("/answers")
public class QuestionAnswerController {

	@Autowired
	private QuestionAnswerService questionAnswerService;
	
	@PostMapping
	public ResponseEntity<InsertResDto> register(@RequestBody List<InsertQuestionAnswerReqDto> data) {
		final InsertResDto response = questionAnswerService.submitAnswer(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
