package com.lawencon.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.AppliedVacancyDao;
import com.lawencon.admin.dao.CandidateDao;
import com.lawencon.admin.dao.JobVacancyDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.appliedvacancy.InsertAppliedVacancyReqDto;
import com.lawencon.admin.model.Candidate;

@Service
public class AppliedVacancyService {

	@Autowired
	private AppliedVacancyDao appliedVacancyDao;
	
	@Autowired
	private CandidateDao candidateDao;
	
	@Autowired
	private JobVacancyDao jobVacancyDao;
	
	public InsertResDto insertAppliedVacancy(InsertAppliedVacancyReqDto data) {
		final InsertResDto response = new InsertResDto();
		
		return response;
	}
}
