package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.AvailableStatusDao;
import com.lawencon.admin.dto.availablestatus.AvailableStatusResDto;

@Service
public class AvailableStatusService {
	@Autowired
	private AvailableStatusDao availableStatusDao;
	
	public List<AvailableStatusResDto> getAll() {
		final List<AvailableStatusResDto> responses = new ArrayList<>();
		
		availableStatusDao.getAll().forEach(av -> {
			final AvailableStatusResDto response = new AvailableStatusResDto();
			//
			
			responses.add(response);
		});
		
		return responses;
	}
}
