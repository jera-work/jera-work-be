package com.lawencon.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.AppliedProgressDao;
import com.lawencon.admin.dao.AppliedStatusDao;
import com.lawencon.admin.dao.AppliedVacancyDao;
import com.lawencon.admin.dao.CandidateDao;
import com.lawencon.admin.dao.JobVacancyDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.UpdateResDto;
import com.lawencon.admin.dto.appliedvacancy.InsertAppliedVacancyReqDto;
import com.lawencon.admin.dto.appliedvacancy.UpdateProgressReqDto;
import com.lawencon.admin.model.AppliedProgress;
import com.lawencon.admin.model.AppliedStatus;
import com.lawencon.admin.model.AppliedVacancy;
import com.lawencon.admin.model.Candidate;
import com.lawencon.admin.model.JobVacancy;
import com.lawencon.base.ConnHandler;

@Service
public class AppliedVacancyService {
	
	@Autowired
	private ApiService apiService;
	
	@Autowired
	private AppliedProgressDao appliedProgressDao;
	
	@Autowired
	private AppliedStatusDao appliedStatusDao;

	@Autowired
	private AppliedVacancyDao appliedVacancyDao;
	
	@Autowired
	private CandidateDao candidateDao;
	
	@Autowired
	private JobVacancyDao jobVacancyDao;
	
	public InsertResDto insertAppliedVacancy(InsertAppliedVacancyReqDto data) {
		final InsertResDto response = new InsertResDto();
		
		ConnHandler.begin();
		try {
			final AppliedVacancy appliedVacancy = new AppliedVacancy();
			
			final AppliedProgress appliedProgress = appliedProgressDao.getById(data.getAppliedProgressId());
			appliedVacancy.setAppliedProgress(appliedProgress);
			
			final AppliedStatus appliedStatus = appliedStatusDao.getById(data.getAppliedStatusId());
			appliedVacancy.setAppliedStatus(appliedStatus);
			
			final Candidate candidate =  candidateDao.getByEmail(data.getCandidateEmail());
			appliedVacancy.setCandidate(candidate);
			
			final JobVacancy jobVacancy = jobVacancyDao.getById(data.getJobVacancyId());
			appliedVacancy.setJobVacancy(jobVacancy);
			
			final AppliedVacancy appliedJob = appliedVacancyDao.save(appliedVacancy);
			
			response.setId(appliedJob.getId());
			response.setMessage("Job applied successfully");
			ConnHandler.commit();
		} catch (Exception e) {
			e.printStackTrace();
			ConnHandler.rollback();
		}
		
		return response;
	}

	public UpdateResDto changeAppliedStatusProgress(UpdateProgressReqDto data) {
		final UpdateResDto response = new UpdateResDto();
		
		ConnHandler.begin();
		final AppliedVacancy appliedVacancy = appliedVacancyDao.getById(data.getAppliedVacancyId());
		
		final AppliedProgress appliedProgress = appliedProgressDao.getById(data.getAppliedProgressId());
		appliedVacancy.setAppliedProgress(appliedProgress);
		
		final AppliedVacancy updatedAppliedVacancy = appliedVacancyDao.saveAndFlush(appliedVacancy);
		
		data.setJobVacancyCode(appliedVacancy.getJobVacancy().getVacancyCode());
		
		final HttpStatus responseCandidate = apiService.patchTo("http://localhost:8080/applied-jobs", data);
		
		if(responseCandidate.equals(HttpStatus.OK)) {
			response.setVer(updatedAppliedVacancy.getVersion());
			response.setMessage("Progress updated successfully");
			ConnHandler.commit();
		} else {
			ConnHandler.rollback();
			
			throw new RuntimeException("Insert Failed");
		}
		
		return response;
	}
}
