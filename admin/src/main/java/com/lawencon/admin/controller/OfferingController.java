package com.lawencon.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.DeleteResDto;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.UpdateResDto;
import com.lawencon.admin.dto.offering.DeleteOfferingReqDto;
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
	
	@PostMapping("/delete")
	public ResponseEntity<DeleteResDto> deleteOffering(@RequestBody DeleteOfferingReqDto data) {
		final DeleteResDto response = offeringService.deleteOffering(data.getId());
		return new ResponseEntity<DeleteResDto>(response, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<OfferingResDto> getOfferingByAppliedId(String appliedId){
		final OfferingResDto response = offeringService.getByAppliedId(appliedId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PatchMapping
	public ResponseEntity<UpdateResDto> updateApproved(String offeringId){
		final UpdateResDto response = offeringService.updateIsApproved(offeringId);
		return new ResponseEntity<UpdateResDto>(response, HttpStatus.OK);
	}
}
