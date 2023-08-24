package com.lawencon.candidate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.dao.CandidateDao;
import com.lawencon.candidate.dao.JobVacancyDao;
import com.lawencon.candidate.dao.SavedJobsDao;
import com.lawencon.candidate.dto.DeleteResDto;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.jobvacancy.JobVacancyResDto;
import com.lawencon.candidate.dto.savedjob.InsertSavedJobReqDto;
import com.lawencon.candidate.dto.savedjob.SavedJobResDto;
import com.lawencon.candidate.exception.CustomException;
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

	@Autowired
	private ApiService apiService;

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

		SavedJobResDto response = null;
		if (savedJobs != null) {
			response = new SavedJobResDto();
			response.setId(savedJobs.getId());
		}

		return response;
	}

	public List<SavedJobResDto> getMySavedJob(int startIndex, int endIndex) {
		final List<SavedJobResDto> responses = new ArrayList<>();
		final Candidate candidate = candidateDao.getById(principalService.getAuthPrincipal());

		savedJobsDao.getByCandidateId(startIndex, endIndex, candidate.getId()).forEach(sj -> {
			final String url = "http://localhost:8081/jobs/code/?code=" + sj.getJobVacancy().getVacancyCode();
			final JobVacancyResDto responseFromAdmin = apiService.getFrom(url, JobVacancyResDto.class);

			final SavedJobResDto response = new SavedJobResDto();
			response.setCityName(responseFromAdmin.getCityName());
			response.setCompanyName(responseFromAdmin.getCompanyName());
			response.setCompanyPhotoId(responseFromAdmin.getCompanyPhotoId());
			response.setDegreeName(responseFromAdmin.getDegreeName());
			response.setJobTypeName(responseFromAdmin.getJobTypeName());
			response.setId(sj.getId());
			response.setSalary(responseFromAdmin.getSalary());
			response.setVacancyCode(sj.getJobVacancy().getVacancyCode());
			response.setVacancyId(sj.getJobVacancy().getId());
			response.setVacancyTitle(sj.getJobVacancy().getVacancyTitle());
//			response.setCreatedAt(url);

			responses.add(response);
		});

		return responses;
	}

	public DeleteResDto deleteSavedJobs(String savedId) {

		ConnHandler.begin();
		final DeleteResDto response = new DeleteResDto();
		if (savedJobsDao.deleteById(savedId)) {
			response.setMessage("Job Unsaved Successfully!");
		} else {
			throw new CustomException("Failed");
		}
		ConnHandler.commit();

		return response;
	}
}
