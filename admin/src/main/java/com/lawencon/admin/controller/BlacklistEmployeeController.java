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
import com.lawencon.admin.dto.blacklistemployee.BlacklistEmployeeResDto;
import com.lawencon.admin.dto.blacklistemployee.InsertBlacklistEmployeeReqDto;
import com.lawencon.admin.dto.hiredemployee.HiredEmployeeResDto;
import com.lawencon.admin.service.BlacklistService;

@RestController
@RequestMapping("/blacklist")
public class BlacklistEmployeeController {

	@Autowired
	private BlacklistService employeeService;

	@PostMapping("/blacklisted")
	public ResponseEntity<InsertResDto> blacklistEmployee(@RequestBody InsertBlacklistEmployeeReqDto data) {
		final InsertResDto response = employeeService.blacklistEmployee(data);
		return new ResponseEntity<InsertResDto>(response, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<BlacklistEmployeeResDto>> getAll(int startIndex, int endIndex)  {
		final List<BlacklistEmployeeResDto> responses = employeeService.getByCompany(startIndex, endIndex);
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
}
