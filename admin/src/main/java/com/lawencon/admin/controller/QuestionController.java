package com.lawencon.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.question.InsertQuestionReqDto;
import com.lawencon.admin.service.QuestionService;

@RestController
@RequestMapping("/questions")
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@PostMapping
	public ResponseEntity<InsertResDto> createQuestion(@RequestBody InsertQuestionReqDto data){
		final InsertResDto response = questionService.createQuestion(data);
		return new ResponseEntity<InsertResDto>(response, HttpStatus.CREATED);
	}
}
