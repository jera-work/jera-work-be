package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.JobTypeDao;
import com.lawencon.admin.dto.jobtype.JobTypeResDto;

@Service
public class JobTypeService {
	@Autowired
	private JobTypeDao jobTypeDao;
	
	public List<JobTypeResDto> getAll() {
		final List<JobTypeResDto> responses = new ArrayList<>();
		
		jobTypeDao.getAll().forEach(av -> {
			final JobTypeResDto response = new JobTypeResDto();
			response.setId(av.getId());
			response.setTypeCode(av.getTypeCode());
			response.setTypeName(av.getTypeName());
			
			responses.add(response);
		});
		
		return responses;
	}
}
