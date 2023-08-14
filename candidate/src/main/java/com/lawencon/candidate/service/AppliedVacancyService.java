package com.lawencon.candidate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.constant.AppliedProgressCode;
import com.lawencon.candidate.constant.AppliedStatusCode;
import com.lawencon.candidate.dao.AppliedProgressDao;
import com.lawencon.candidate.dao.AppliedStatusDao;
import com.lawencon.candidate.dao.AppliedVacancyDao;
import com.lawencon.candidate.dao.CandidateDao;
import com.lawencon.candidate.dao.JobVacancyDao;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.UpdateResDto;
import com.lawencon.candidate.dto.appliedvacancy.InsertAppliedVacancyReqDto;
import com.lawencon.candidate.model.AppliedProgress;
import com.lawencon.candidate.model.AppliedStatus;
import com.lawencon.candidate.model.AppliedVacancy;
import com.lawencon.candidate.model.Candidate;
import com.lawencon.candidate.model.JobVacancy;
import com.lawencon.security.principal.PrincipalService;

@Service
public class AppliedVacancyService {

	@Autowired
	private AppliedVacancyDao appliedVacancyDao;
	
	@Autowired
	private AppliedStatusDao appliedStatusDao;
	
	@Autowired
	private AppliedProgressDao appliedProgressDao;
	
	@Autowired
	private PrincipalService<String> principalService;

	@Autowired
	private CandidateDao candidateDao;

	@Autowired
	private JobVacancyDao jobVacancyDao;
	
	@Autowired
	private ApiService apiService;

	public InsertResDto insertAppliedVacancy(InsertAppliedVacancyReqDto data) {
		final InsertResDto response = new InsertResDto();
		
		ConnHandler.begin();
		try {
			final AppliedVacancy appliedVacancy = new AppliedVacancy();
			
			final JobVacancy jobVacancy = jobVacancyDao.getById(data.getJobVacancyId());
			appliedVacancy.setJobVacancy(jobVacancy);
			
			final AppliedProgress appliedProgress = appliedProgressDao.getByCode(AppliedProgressCode.APPLICATION.progressCode);
			appliedVacancy.setAppliedProgress(appliedProgress);
			
			final AppliedStatus appliedStatus = appliedStatusDao.getByCode(AppliedStatusCode.ACTIVE.statusCode);
			appliedVacancy.setAppliedStatus(appliedStatus);
			
			final Candidate candidate = candidateDao.getById(principalService.getAuthPrincipal());
			appliedVacancy.setCandidate(candidate);
			
			final AppliedVacancy insertedAppliedVacancy = appliedVacancyDao.save(appliedVacancy);
			
			response.setId(insertedAppliedVacancy.getId());
			response.setMessage("Job applied Successfully");

			data.setAppliedProgressCode(AppliedProgressCode.APPLICATION.progressCode);
			data.setAppliedStatusCode(AppliedStatusCode.ACTIVE.statusCode);
			data.setJobVacancyCode(jobVacancy.getVacancyCode());
			
			apiService.writeTo("http://localhost:8080/jobs", data);
			
			ConnHandler.commit();
		} catch (Exception e) {
			
		}
		return response;
	}
	
}
