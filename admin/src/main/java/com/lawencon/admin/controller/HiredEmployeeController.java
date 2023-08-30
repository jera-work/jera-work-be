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
import com.lawencon.admin.dto.hiredemployee.HiredEmployeeResDto;
import com.lawencon.admin.dto.hiredemployee.InsertHiredEmployeeReqDto;
import com.lawencon.admin.service.HiredEmployeeService;

@RestController
@RequestMapping("/hired-employees")
public class HiredEmployeeController {

	@Autowired
	private HiredEmployeeService hiredEmployeeService;
	
	@PostMapping
	public ResponseEntity<InsertResDto> hireEmployee(@RequestBody InsertHiredEmployeeReqDto data) {
		final InsertResDto response = hiredEmployeeService.hireEmployee(data);
		return new ResponseEntity<InsertResDto>(response, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<HiredEmployeeResDto>> getAll(int startIndex, int endIndex)  {
		final List<HiredEmployeeResDto> responses = hiredEmployeeService.getByCompany(startIndex, endIndex);
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
	
	@GetMapping("/employee")
	public ResponseEntity<HiredEmployeeResDto> getByCandidateId(String candidateId){
		final HiredEmployeeResDto response = hiredEmployeeService.getByCandidateId(candidateId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
