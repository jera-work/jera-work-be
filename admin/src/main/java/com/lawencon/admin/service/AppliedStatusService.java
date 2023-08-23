package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.AppliedProgressDao;
import com.lawencon.admin.dao.AppliedStatusDao;
import com.lawencon.admin.dto.appliedprogress.AppliedProgressResDto;
import com.lawencon.admin.dto.appliedstatus.AppliedStatusResDto;

@Service
public class AppliedStatusService {
	@Autowired
	private AppliedStatusDao appliedStatusDao;
	
	public List<AppliedStatusResDto> getAll() {
		final List<AppliedStatusResDto> responses = new ArrayList<>();
		
		appliedStatusDao.getAll().forEach(av -> {
			final AppliedStatusResDto response = new AppliedStatusResDto();
			response.setId(av.getId());
			response.setStatusCode(av.getStatusCode());
			response.setStatusName(av.getStatusName());
			
			responses.add(response);
		});
		
		return responses;
	}
	
	public AppliedStatusResDto getByCode(String code) {
		final AppliedStatusResDto response = new AppliedStatusResDto();
		
		response.setId(appliedStatusDao.getByCode(code).getId());
		
		return response;
	}
}
