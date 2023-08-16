package com.lawencon.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.mcuvacancy.InsertMcuVacancyReqDto;
import com.lawencon.admin.service.McuVacancyService;

@RestController
@RequestMapping("/mcus")
public class McuVacancyController {

	@Autowired
	private McuVacancyService mcuService;

	@PostMapping
	public ResponseEntity<InsertResDto> insertMcu(@RequestBody InsertMcuVacancyReqDto data) {
		final InsertResDto response = mcuService.insertMcu(data);
		return new ResponseEntity<InsertResDto>(response, HttpStatus.CREATED);
	}

}
