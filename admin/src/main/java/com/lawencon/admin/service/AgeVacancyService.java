package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.AgeVacancyDao;
import com.lawencon.admin.dto.agevacancy.AgeVacancyResDto;

@Service
public class AgeVacancyService {
	
	@Autowired
	private AgeVacancyDao ageVacancyDao;
	
	public List<AgeVacancyResDto> getAll() {
		final List<AgeVacancyResDto> responses = new ArrayList<>();
		
		ageVacancyDao.getAll().forEach(av -> {
			final AgeVacancyResDto response = new AgeVacancyResDto();
			//
			
			responses.add(response);
		});
		
		return responses;
	}
}
