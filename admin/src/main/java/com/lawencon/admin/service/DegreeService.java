package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.DegreeDao;
import com.lawencon.admin.dto.degree.DegreeResDto;

@Service
public class DegreeService {

	@Autowired
	private DegreeDao degreeDao;
	
	public List<DegreeResDto> getAll() {
		final List<DegreeResDto> responses = new ArrayList<>();
		
		degreeDao.getAll().forEach(av -> {
			final DegreeResDto response = new DegreeResDto();
			//
			
			responses.add(response);
		});
		
		return responses;
	}
}
