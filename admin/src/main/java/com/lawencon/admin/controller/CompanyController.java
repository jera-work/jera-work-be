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
import com.lawencon.admin.dto.company.CompanyCreateReqDto;
import com.lawencon.admin.dto.company.CompanyResDto;
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
	
	@GetMapping
	public ResponseEntity<List<CompanyResDto>> getCompanies(){
		final List<CompanyResDto> responses = companyService.getCompanies();
		return new ResponseEntity<List<CompanyResDto>>(responses, HttpStatus.OK);
	}

}
