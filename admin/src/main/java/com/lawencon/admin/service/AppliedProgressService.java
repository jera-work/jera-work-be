package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.AppliedProgressDao;
import com.lawencon.admin.dto.appliedprogress.AppliedProgressResDto;

@Service
public class AppliedProgressService {
	@Autowired
	private AppliedProgressDao appliedProgressDao;
	
	public List<AppliedProgressResDto> getAll() {
		final List<AppliedProgressResDto> responses = new ArrayList<>();
		
		appliedProgressDao.getAll().forEach(av -> {
			final AppliedProgressResDto response = new AppliedProgressResDto();
			response.setId(av.getId());
			response.setProgressCode(av.getProgressCode());
			response.setProgressName(av.getProgressName());
			
			responses.add(response);
		});
		
		return responses;
	}
	
	public AppliedProgressResDto getByCode(String code) {
		final AppliedProgressResDto response = new AppliedProgressResDto();
		
		response.setId(appliedProgressDao.getByCode(code).getId());
		
		return response;
	}
}
