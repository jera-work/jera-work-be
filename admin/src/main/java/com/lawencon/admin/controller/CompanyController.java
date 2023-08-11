package com.lawencon.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.company.CompanyCreateReqDto;
import com.lawencon.admin.service.CompanyService;

@RestController
@RequestMapping("/companies")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@PostMapping
	public ResponseEntity<InsertResDto> createCompany(@RequestBody CompanyCreateReqDto data){
		final InsertResDto response = companyService.createCompany(data);
		return new ResponseEntity<InsertResDto>(response, HttpStatus.CREATED);
	}

}
