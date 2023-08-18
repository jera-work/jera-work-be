package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.MaritalDao;
import com.lawencon.admin.dto.marital.MaritalResDto;

@Service
public class MaritalService {
	@Autowired
	private MaritalDao maritalDao;
	
	public List<MaritalResDto> getAll() {
		final List<MaritalResDto> responses = new ArrayList<>();
		
		maritalDao.getAll().forEach(av -> {
			final MaritalResDto response = new MaritalResDto();
			//
			
			responses.add(response);
		});
		
		return responses;
	}
}
