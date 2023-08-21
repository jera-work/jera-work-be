package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.NationalityDao;
import com.lawencon.admin.dto.nationality.NationalityResDto;

@Service
public class NationalityService {
	@Autowired
	private NationalityDao nationalityDao;
	
	public List<NationalityResDto> getAll() {
		final List<NationalityResDto> responses = new ArrayList<>();
		
		nationalityDao.getAll().forEach(av -> {
			final NationalityResDto response = new NationalityResDto();
			response.setId(av.getId());
			response.setNationalityCode(av.getNationalityCode());
			response.setNationalityName(av.getNationalityName());
			responses.add(response);
		});
		
		return responses;
	}
}
