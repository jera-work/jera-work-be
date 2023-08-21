package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.ExperienceLevelDao;
import com.lawencon.admin.dto.experiencelevel.ExperienceLevelResDto;

@Service
public class ExperienceLevelService {
	@Autowired
	private ExperienceLevelDao experienceLevelDao;
	
	public List<ExperienceLevelResDto> getAll() {
		final List<ExperienceLevelResDto> responses = new ArrayList<>();
		
		experienceLevelDao.getAll().forEach(av -> {
			final ExperienceLevelResDto response = new ExperienceLevelResDto();
			//
			
			responses.add(response);
		});
		
		return responses;
	}
}
