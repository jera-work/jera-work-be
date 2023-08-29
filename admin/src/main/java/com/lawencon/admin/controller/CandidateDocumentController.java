package com.lawencon.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.candidatedocument.CandidateDocumentCreateReqDto;
import com.lawencon.admin.dto.candidatedocument.CandidateDocumentResDto;
import com.lawencon.admin.service.CandidateDocumentService;

@RestController
@RequestMapping("/documents")
public class CandidateDocumentController {
	
	@Autowired
	private CandidateDocumentService docService;
	
	@PostMapping
	public ResponseEntity<InsertResDto> insertCandidateDocs(@RequestBody List<CandidateDocumentCreateReqDto> data) {
		final InsertResDto response = docService.insertCandidateDocs(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<CandidateDocumentResDto>> getDocuments(@RequestParam String email) {
		final List<CandidateDocumentResDto> responses = docService.getDocuments(email);
		return new ResponseEntity<List<CandidateDocumentResDto>>(responses, HttpStatus.OK);
	}
}
