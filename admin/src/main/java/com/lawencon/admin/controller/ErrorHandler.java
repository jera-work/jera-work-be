package com.lawencon.admin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lawencon.admin.dto.ErrorResDto;
import com.lawencon.admin.exception.CustomException;

@ControllerAdvice
public class ErrorHandler {
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorResDto<String>> handleTicketLimitException(CustomException exception){
		final ErrorResDto<String> response = new ErrorResDto<>();
		response.setMessage(exception.getMessage());
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
