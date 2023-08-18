package com.lawencon.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.availablestatus.AvailableStatusResDto;
import com.lawencon.admin.service.AvailableStatusService;

@RestController
@RequestMapping("/available-statuses")
public class AvailableStatusController {

	@Autowired
	private AvailableStatusService availableStatusService;
	
	@GetMapping
	public ResponseEntity<List<AvailableStatusResDto>> getAll(){
		final List<AvailableStatusResDto> responses = availableStatusService.getAll();
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
}
