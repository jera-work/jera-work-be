package com.lawencon.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.AppliedVacancyDao;
import com.lawencon.admin.dao.InterviewVacancyDao;
import com.lawencon.admin.dao.JobVacancyDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.UpdateResDto;
import com.lawencon.admin.dto.assessmentvacancy.UpdateNotesProgressReqDto;
import com.lawencon.admin.dto.email.EmailReqDto;
import com.lawencon.admin.dto.email.InterviewVacancyReqDto;
import com.lawencon.admin.dto.interviewvacancy.InsertInterviewVacancyReqDto;
import com.lawencon.admin.dto.interviewvacancy.InterviewVacancyResDto;
import com.lawencon.admin.model.AppliedVacancy;
import com.lawencon.admin.model.InterviewVacancy;
import com.lawencon.admin.model.JobVacancy;
import com.lawencon.admin.util.DateUtil;
import com.lawencon.base.ConnHandler;

@Service
public class InterviewVacancyService {
	
	@Autowired
	private AppliedVacancyDao appliedVacancyDao;
	@Autowired
	private InterviewVacancyDao interviewDao;
	@Autowired
	private JobVacancyDao jobVacancyDao;
	@Autowired
	private SendMailService mailService;

	public InsertResDto insertInterview(InsertInterviewVacancyReqDto data) {
		
		ConnHandler.begin();
		final AppliedVacancy appliedVacancy = appliedVacancyDao.getById(data.getAppliedVacancyId());
		final InterviewVacancy interview = new InterviewVacancy();
		interview.setAppliedVacancy(appliedVacancy);
		interview.setEndDate(DateUtil.dateParse(data.getEndDate()));
		interview.setInterviewLocation(data.getInterviewLocation());
		interview.setNotes(data.getNotes());
		interview.setStartDate(DateUtil.dateParse(data.getStartDate()));
		final InterviewVacancy interviewDb = interviewDao.saveAndFlush(interview);
		
		ConnHandler.commit();
		
		sendInterview(interviewDb, appliedVacancy.getCandidate().getCandidateEmail());
		
		final InsertResDto response = new InsertResDto();
		response.setId(interviewDb.getId());
		response.setMessage("Interview has been created!");
		
		return response;
		
	}
	
	public void sendInterview(InterviewVacancy interviewVacancyDb, String email) {
		final InterviewVacancyReqDto interviewVacancyReqDto = new InterviewVacancyReqDto();
		
		final AppliedVacancy applied = appliedVacancyDao.getById(interviewVacancyDb.getAppliedVacancy().getId());
		final JobVacancy job = jobVacancyDao.getById(applied.getJobVacancy().getId());
		
		interviewVacancyReqDto.setCompanyName(job.getCompany().getCompanyName());
		interviewVacancyReqDto.setCompanyPhoto(job.getCompany().getPhoto().getFileContent());
		interviewVacancyReqDto.setVacancyTitle(job.getVacancyTitle());
		interviewVacancyReqDto.setLevelName(job.getExpLevel().getLevelName());
		interviewVacancyReqDto.setStartDate(interviewVacancyDb.getStartDate());
		interviewVacancyReqDto.setEndDate(interviewVacancyDb.getEndDate());
		interviewVacancyReqDto.setNotes(interviewVacancyDb.getNotes());
		interviewVacancyReqDto.setInterviewLocation(interviewVacancyDb.getInterviewLocation());
		
		try {				
			final EmailReqDto emailReqDto = new EmailReqDto();
			emailReqDto.setSubject("You has invited to interview by company");
			emailReqDto.setEmail(email);
			mailService.sendInterview(emailReqDto, interviewVacancyReqDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public InterviewVacancyResDto getByAppliedId(String appliedVacancyId) {
		final InterviewVacancy interviewVacancy = interviewDao.getByAppliedVacancyId(appliedVacancyId);
		
		if(interviewVacancy != null) {
			final InterviewVacancyResDto response = new InterviewVacancyResDto();
			response.setStartDate(DateUtil.dateFormat(interviewVacancy.getStartDate()));
			response.setEndDate(DateUtil.dateFormat(interviewVacancy.getEndDate()));
			response.setNotes(interviewVacancy.getNotes());
			response.setLocation(interviewVacancy.getInterviewLocation());
			response.setInterviewId(interviewVacancy.getId());
			return response;			
		} else {
			return null;
		}
	}
	
	public UpdateResDto updateNotes(UpdateNotesProgressReqDto data) {
		ConnHandler.begin();
		final UpdateResDto response = new UpdateResDto();
		
		final InterviewVacancy interview = interviewDao.getById(data.getProgressId());
		interview.setNotes(data.getNotes());
		final InterviewVacancy interviewDb = interviewDao.saveAndFlush(interview);
		
		if(interviewDb != null) {
			response.setVer(interviewDb.getVersion());
			response.setMessage("Notes has been updated!");
			ConnHandler.commit();			
		} else {
			ConnHandler.rollback();
		}
		
		return response;
	}
}
