package com.lawencon.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.DeleteResDto;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.mcuvacancy.DeleteMcuVacancyReqDto;
import com.lawencon.admin.dto.mcuvacancy.InsertMcuVacancyReqDto;
import com.lawencon.admin.dto.mcuvacancy.McuVacancyResDto;
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
	
	@PostMapping("/delete")
	public ResponseEntity<DeleteResDto> deleteMcu(@RequestBody DeleteMcuVacancyReqDto data) {
		final DeleteResDto response = mcuService.deleteMcu(data.getId());
		return new ResponseEntity<DeleteResDto>(response, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<McuVacancyResDto> getMcuByAppliedId(String appliedId){
		final McuVacancyResDto response = mcuService.getByAppliedId(appliedId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
