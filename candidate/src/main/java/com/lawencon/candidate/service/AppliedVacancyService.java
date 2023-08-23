package com.lawencon.candidate.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.constant.AppliedProgressCode;
import com.lawencon.candidate.constant.AppliedStatusCode;
import com.lawencon.candidate.dao.AppliedVacancyDao;
import com.lawencon.candidate.dao.CandidateDao;
import com.lawencon.candidate.dao.JobVacancyDao;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.UpdateResDto;
import com.lawencon.candidate.dto.appliedprogress.AppliedProgressResDto;
import com.lawencon.candidate.dto.appliedstatus.AppliedStatusResDto;
import com.lawencon.candidate.dto.appliedvacancy.AppliedVacancyResDto;
import com.lawencon.candidate.dto.appliedvacancy.InsertAppliedVacancyReqDto;
import com.lawencon.candidate.dto.appliedvacancy.UpdateProgressReqDto;
import com.lawencon.candidate.dto.jobvacancy.JobSearchResDto;
import com.lawencon.candidate.dto.jobvacancy.JobVacancyResDto;
import com.lawencon.candidate.exception.CustomException;
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
	@Autowired
	private JobVacancyService jobVacancyService;

	public InsertResDto insertAppliedVacancy(InsertAppliedVacancyReqDto data) {
		final InsertResDto response = new InsertResDto();

		ConnHandler.begin();
		final Candidate candidate = candidateDao.getById(principalService.getAuthPrincipal());
		final JobVacancy job = jobVacancyDao.getById(data.getJobVacancyId());
		final AppliedVacancy checkAppliedVacancy = appliedVacancyDao.getByJobVacancyAndCandidate(data.getJobVacancyId(), principalService.getAuthPrincipal());
		
		if(checkAppliedVacancy == null) {
			final String appProgressUrl = "http://localhost:8081/applied-progress/code/?code=" + AppliedProgressCode.APPLICATION.progressCode;
			final AppliedProgressResDto appProgressResponse = apiService.getFrom(appProgressUrl, AppliedProgressResDto.class);

			final String appStatusUrl = "http://localhost:8081/applied-status/code/?code=" + AppliedStatusCode.ACTIVE.statusCode;
			final AppliedStatusResDto appStatusResponse = apiService.getFrom(appStatusUrl, AppliedStatusResDto.class);

			final AppliedVacancy appliedVacancy = new AppliedVacancy();
			appliedVacancy.setCandidate(candidate);
			appliedVacancy.setAppliedProgress(appProgressResponse.getId());
			appliedVacancy.setAppliedStatus(appStatusResponse.getId());
			appliedVacancy.setJobVacancy(job);
			final AppliedVacancy appliedVacancyDb = appliedVacancyDao.save(appliedVacancy);

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
		} else {
			throw new CustomException("Error! You already applied for this job!");
		}

		return response;
	}

	public UpdateResDto changeAppliedStatusProgress(UpdateProgressReqDto data) {

		ConnHandler.begin();
		
		final JobVacancy jobVacancy = jobVacancyDao.getByCode(data.getJobVacancyCode());
		
		final Candidate candidate = candidateDao.getByEmail(data.getCandidateEmail());
		
		final AppliedVacancy appliedVacancyId = appliedVacancyDao.getByJobVacancyAndCandidate(jobVacancy.getId(), candidate.getId());
		
		final AppliedVacancy appliedVacancy = appliedVacancyDao.getById(appliedVacancyId.getId());
		appliedVacancy.setAppliedProgress(data.getAppliedProgressId());
		final AppliedVacancy updatedAppliedVacancy = appliedVacancyDao.saveAndFlush(appliedVacancy);
		ConnHandler.commit();

		final UpdateResDto response = new UpdateResDto();
		response.setVer(updatedAppliedVacancy.getVersion());
		response.setMessage("Progress updated successfully");

		return response;
	}
	
	public List<AppliedVacancyResDto> getByCandidateId(){
		final Candidate candidate = candidateDao.getById(principalService.getAuthPrincipal());
		
		final String encodedEmail = Base64.getEncoder().encodeToString(candidate.getCandidateEmail().getBytes());
		final String url = "http://localhost:8081/applied/my-applied/?email=" + encodedEmail;
		final List<AppliedVacancyResDto> responseFromAdmins = apiService.getListFrom(url, AppliedVacancyResDto.class);
		
		final List<AppliedVacancyResDto> responseFromAdminConverteds = new ObjectMapper().convertValue(responseFromAdmins,
				new TypeReference<List<AppliedVacancyResDto>>() {});
		
		final List<AppliedVacancyResDto> myAppliedFromCdts = new ArrayList<>();
		appliedVacancyDao.getByCandidateId(principalService.getAuthPrincipal()).forEach(av -> {
			final AppliedVacancyResDto myAppliedFromCdt = new AppliedVacancyResDto();
			myAppliedFromCdt.setId(av.getId());
			myAppliedFromCdt.setJobVacancyId(av.getJobVacancy().getId());
			myAppliedFromCdt.setJobVacancyCode(av.getJobVacancy().getVacancyCode());
			final JobVacancyResDto jobResponse = jobVacancyService.getJobDetail(av.getJobVacancy().getId());
			myAppliedFromCdt.setCityName(jobResponse.getCityName());
			myAppliedFromCdt.setCompanyName(jobResponse.getCompanyName());
			myAppliedFromCdt.setCompanyPhotoId(jobResponse.getCompanyPhotoId());
			myAppliedFromCdt.setDegreeName(jobResponse.getDegreeName());
			myAppliedFromCdt.setJobTypeName(jobResponse.getJobTypeName());
			myAppliedFromCdt.setVacancyTitle(jobResponse.getVacancyTitle());
			myAppliedFromCdt.setSalary(jobResponse.getSalary());
			
			myAppliedFromCdts.add(myAppliedFromCdt);
		});
		
		final List<AppliedVacancyResDto> responses = new ArrayList<>();
		
		responseFromAdminConverteds.forEach(resCon -> {
			myAppliedFromCdts.forEach(appCdt -> {
				if(resCon.getJobVacancyCode().equals(appCdt.getJobVacancyCode())) {
					appCdt.setAppliedProgressName(resCon.getAppliedProgressName());
					appCdt.setAppliedStatusName(resCon.getAppliedStatusName());
					final AppliedVacancyResDto response = appCdt;
					
					responses.add(response);
				}
			});
		});
		
		return responses;
	}
	
}
