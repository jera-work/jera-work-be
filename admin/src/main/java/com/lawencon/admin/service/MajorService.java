package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.MajorDao;
import com.lawencon.admin.dto.major.MajorResDto;

@Service
public class MajorService {
	@Autowired
	private MajorDao majorDao;
	
	public List<MajorResDto> getAll() {
		final List<MajorResDto> responses = new ArrayList<>();
		
		majorDao.getAll().forEach(av -> {
			final MajorResDto response = new MajorResDto();
			//
			
			responses.add(response);
		});
		
		return responses;
	}
}
