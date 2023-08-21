package com.lawencon.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.documenttype.DocumentTypeResDto;
import com.lawencon.admin.service.DocumentTypeService;

@RestController
@RequestMapping("/document-types")
public class DocumentTypeController {

	@Autowired
	private DocumentTypeService documentTypeService;
	
	@GetMapping
	public ResponseEntity<List<DocumentTypeResDto>> getAll(){
		final List<DocumentTypeResDto> responses = documentTypeService.getAll();
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
}
