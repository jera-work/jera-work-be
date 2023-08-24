package com.lawencon.candidate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.document.CandidateDocumentCreateReqDto;
import com.lawencon.candidate.dto.document.CandidateDocumentResDto;
import com.lawencon.candidate.service.CandidateDocumentService;

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
	public ResponseEntity<List<CandidateDocumentResDto>> getDocuments() {
		final List<CandidateDocumentResDto> responses = docService.getDocuments();
		return new ResponseEntity<List<CandidateDocumentResDto>>(responses, HttpStatus.OK);
	}

}
