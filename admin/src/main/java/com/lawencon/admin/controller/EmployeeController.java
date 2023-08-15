package com.lawencon.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.blacklistemployee.InsertBlacklistEmployeeReqDto;
import com.lawencon.admin.dto.hiredemployee.InsertHiredEmployeeReqDto;
import com.lawencon.admin.service.BlacklistService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private BlacklistService employeeService;

	@PostMapping("/hired")
	public ResponseEntity<InsertResDto> hireEmployee(@RequestBody InsertHiredEmployeeReqDto data) {
		final InsertResDto response = employeeService.hireEmployee(data);
		return new ResponseEntity<InsertResDto>(response, HttpStatus.CREATED);
	}

	@PostMapping("/blacklisted")
	public ResponseEntity<InsertResDto> blacklistEmployee(@RequestBody InsertBlacklistEmployeeReqDto data) {
		final InsertResDto response = employeeService.blacklistEmployee(data);
		return new ResponseEntity<InsertResDto>(response, HttpStatus.CREATED);
	}

}
