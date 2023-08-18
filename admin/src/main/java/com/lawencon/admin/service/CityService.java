package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.CityDao;
import com.lawencon.admin.dto.city.CityResDto;

@Service
public class CityService {
	@Autowired
	private CityDao cityDao;
	
	public List<CityResDto> getAll() {
		final List<CityResDto> responses = new ArrayList<>();
		
		cityDao.getAll().forEach(av -> {
			final CityResDto response = new CityResDto();
			//
			
			responses.add(response);
		});
		
		return responses;
	}
}
