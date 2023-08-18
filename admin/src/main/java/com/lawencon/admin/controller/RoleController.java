package com.lawencon.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.role.RoleResDto;
import com.lawencon.admin.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	@GetMapping
	public ResponseEntity<List<RoleResDto>> getAll(){
		final List<RoleResDto> responses = roleService.getAll();
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
}
