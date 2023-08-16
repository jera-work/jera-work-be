package com.lawencon.candidate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.dao.AppliedVacancyDao;
import com.lawencon.candidate.dao.CandidateDao;
import com.lawencon.candidate.dao.JobVacancyDao;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.UpdateResDto;
import com.lawencon.candidate.dto.appliedvacancy.InsertAppliedVacancyReqDto;
import com.lawencon.candidate.dto.appliedvacancy.UpdateProgressReqDto;
import com.lawencon.candidate.model.AppliedVacancy;
import com.lawencon.candidate.model.Candidate;
import com.lawencon.candidate.model.JobVacancy;
import com.lawencon.security.principal.PrincipalServiceImpl;

@Service
public class AppliedVacancyService {

	@Autowired
	private AppliedVacancyDao appliedVacancyDao;
	@Autowired
	private CandidateDao candidateDao;
	@Autowired
	private JobVacancyDao jobVacancyDao;
	@Autowired
	private ApiService apiService;
	@Autowired
	private PrincipalServiceImpl principalService;

	public InsertResDto insertAppliedVacancy(InsertAppliedVacancyReqDto data) {

		ConnHandler.begin();
		final Candidate candidate = candidateDao.getById(principalService.getAuthPrincipal());
		final JobVacancy job = jobVacancyDao.getById(data.getJobVacancyId());

		final AppliedVacancy appliedVacancy = new AppliedVacancy();
		appliedVacancy.setCandidate(candidate);
		appliedVacancy.setAppliedProgress(data.getAppliedProgressId());
		appliedVacancy.setAppliedStatus(data.getAppliedStatusId());
		appliedVacancy.setJobVacancy(job);
		final AppliedVacancy appliedVacancyDb = appliedVacancyDao.save(appliedVacancy);

		final InsertResDto response = new InsertResDto();
		data.setCandidateEmail(candidate.getCandidateEmail());
		data.setJobVacancyCode(job.getVacancyCode());
		final HttpStatus status = apiService.writeTo("http://localhost:8081/applied/apply", data);

		if (status.equals(HttpStatus.CREATED)) {
			response.setId(appliedVacancyDb.getId());
			response.setMessage("You have applied to this job!");
			ConnHandler.commit();
		} else {
			ConnHandler.rollback();
			throw new RuntimeException("Insert Failed");
		}

		return response;
	}

	public UpdateResDto changeAppliedStatusProgress(UpdateProgressReqDto data) {

		ConnHandler.begin();
		final AppliedVacancy appliedVacancy = appliedVacancyDao.getById(data.getAppliedVacancyId());
		appliedVacancy.setAppliedProgress(data.getAppliedProgressId());
		final AppliedVacancy updatedAppliedVacancy = appliedVacancyDao.saveAndFlush(appliedVacancy);
		ConnHandler.commit();

		final UpdateResDto response = new UpdateResDto();
		response.setVer(updatedAppliedVacancy.getVersion());
		response.setMessage("Progress updated successfully");

		return response;
	}
}
