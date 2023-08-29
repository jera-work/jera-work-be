package com.lawencon.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.offering.InsertOfferingReqDto;
import com.lawencon.admin.dto.offering.OfferingResDto;
import com.lawencon.admin.service.OfferingService;

@RestController
@RequestMapping("/offerings")
public class OfferingController {

	@Autowired
	private OfferingService offeringService;

	@PostMapping
	public ResponseEntity<InsertResDto> insertOffering(@RequestBody InsertOfferingReqDto data) {
		final InsertResDto response = offeringService.insertOffering(data);
		return new ResponseEntity<InsertResDto>(response, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<OfferingResDto> getOfferingByAppliedId(String appliedId){
		final OfferingResDto response = offeringService.getByAppliedId(appliedId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
