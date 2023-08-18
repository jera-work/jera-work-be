package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.ReligionDao;
import com.lawencon.admin.dto.ReligionResDto;

@Service
public class ReligionService {
	@Autowired
	private ReligionDao religionDao;
	
	public List<ReligionResDto> getAll() {
		final List<ReligionResDto> responses = new ArrayList<>();
		
		religionDao.getAll().forEach(av -> {
			final ReligionResDto response = new ReligionResDto();
			//
			
			responses.add(response);
		});
		
		return responses;
	}
}
