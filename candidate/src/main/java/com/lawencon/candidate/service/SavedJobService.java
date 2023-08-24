package com.lawencon.candidate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.dao.CandidateDao;
import com.lawencon.candidate.dao.JobVacancyDao;
import com.lawencon.candidate.dao.SavedJobsDao;
import com.lawencon.candidate.dto.DeleteResDto;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.savedjob.InsertSavedJobReqDto;
import com.lawencon.candidate.dto.savedjob.SavedJobResDto;
import com.lawencon.candidate.model.Candidate;
import com.lawencon.candidate.model.JobVacancy;
import com.lawencon.candidate.model.SavedJobs;
import com.lawencon.security.principal.PrincipalService;

@Service
public class SavedJobService {

	@Autowired
	private PrincipalService<String> principalService;
	
	@Autowired
	private JobVacancyDao jobVacancyDao;
	
	@Autowired
	private SavedJobsDao savedJobsDao;
	
	@Autowired
	private CandidateDao candidateDao;
	
	public InsertResDto createSavedJob(InsertSavedJobReqDto data) {
		final InsertResDto response = new InsertResDto();
		
		ConnHandler.begin();
		final SavedJobs savedJobs = new SavedJobs();
		
		final Candidate candidate = candidateDao.getByIdRef(principalService.getAuthPrincipal());
		savedJobs.setCandidate(candidate);
		
		final JobVacancy jobVacancy = jobVacancyDao.getById(data.getJobVacancyId());
		savedJobs.setJobVacancy(jobVacancy);
		
		final SavedJobs insertedSaved = savedJobsDao.save(savedJobs);
		
		ConnHandler.commit();
		
		response.setId(insertedSaved.getId());
		response.setMessage("Job saved successfully");
		return response;
	}
	
	public SavedJobResDto getByCandidateAndJob(String jobId) {
		final SavedJobs savedJobs = savedJobsDao.getbyJobAndCandidate(jobId, principalService.getAuthPrincipal());
		
		final SavedJobResDto response = new SavedJobResDto();
		response.setId(savedJobs.getId());
		
		return response;
	}
	
	public DeleteResDto deleteSavedJobs(String savedId) {
		savedJobsDao.deleteById(savedId);
		
		final DeleteResDto response = new DeleteResDto();
		response.setMessage("Success!");
		
		return response;
	}
}	
