package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.RoleDao;
import com.lawencon.admin.dto.role.RoleResDto;

@Service
public class RoleService {
	@Autowired
	private RoleDao roleDao;
	
	public List<RoleResDto> getAll() {
		final List<RoleResDto> responses = new ArrayList<>();
		
		roleDao.getAll().forEach(av -> {
			final RoleResDto response = new RoleResDto();
			//
			
			responses.add(response);
		});
		
		return responses;
	}
}
