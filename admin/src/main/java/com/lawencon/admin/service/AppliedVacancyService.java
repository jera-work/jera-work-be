package com.lawencon.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.AppliedProgressDao;
import com.lawencon.admin.dao.AppliedStatusDao;
import com.lawencon.admin.dao.AppliedVacancyDao;
import com.lawencon.admin.dao.CandidateDao;
import com.lawencon.admin.dao.JobVacancyDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.appliedvacancy.InsertAppliedVacancyReqDto;
import com.lawencon.admin.model.AppliedVacancy;
import com.lawencon.admin.model.Candidate;
import com.lawencon.admin.model.JobVacancy;
import com.lawencon.base.ConnHandler;

@Service
public class AppliedVacancyService {

	@Autowired
	private AppliedVacancyDao appliedVacancyDao;
	@Autowired
	private CandidateDao candidateDao;
	@Autowired
	private JobVacancyDao jobVacancyDao;
	@Autowired
	private AppliedProgressDao progressDao;
	@Autowired
	private AppliedStatusDao statusDao;

	public InsertResDto insertAppliedVacancy(InsertAppliedVacancyReqDto data) {

		ConnHandler.begin();
		System.out.println(data.getJobVacancyCode());
		final JobVacancy job = jobVacancyDao.getByCode(data.getJobVacancyCode());
		final Candidate candidate = candidateDao.getByEmail(data.getCandidateEmail());
		
		final AppliedVacancy appliedVacancy = new AppliedVacancy();
		appliedVacancy.setCandidate(candidate);
		appliedVacancy.setAppliedProgress(progressDao.getByIdRef(data.getAppliedProgressId()));
		appliedVacancy.setAppliedStatus(statusDao.getByIdRef(data.getAppliedStatusId()));
		appliedVacancy.setJobVacancy(job);
		final AppliedVacancy appliedVacancyDb = appliedVacancyDao.save(appliedVacancy);
		ConnHandler.commit();
		
		final InsertResDto response = new InsertResDto();
		response.setId(appliedVacancyDb.getId());
		response.setMessage("You have applied to this job!");
		return response;
	}
}
