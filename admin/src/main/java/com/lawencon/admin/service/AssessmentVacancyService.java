package com.lawencon.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.AppliedVacancyDao;
import com.lawencon.admin.dao.AssessmentVacancyDao;
import com.lawencon.admin.dao.JobVacancyDao;
import com.lawencon.admin.dto.DeleteResDto;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.assessmentvacancy.AssessmentVacancyResDto;
import com.lawencon.admin.dto.assessmentvacancy.InsertAssessmentVacancyReqDto;
import com.lawencon.admin.dto.email.AssessmentVacancyReqDto;
import com.lawencon.admin.dto.email.EmailReqDto;
import com.lawencon.admin.model.AppliedVacancy;
import com.lawencon.admin.model.AssessmentVacancy;
import com.lawencon.admin.model.JobVacancy;
import com.lawencon.admin.util.DateUtil;
import com.lawencon.base.ConnHandler;

@Service
public class AssessmentVacancyService {

	@Autowired
	private AppliedVacancyDao appliedVacancyDao;
	@Autowired
	private AssessmentVacancyDao assessmentVacancyDao;
	@Autowired
	private JobVacancyDao jobVacancyDao;
	@Autowired
	private SendMailService mailService;

	public InsertResDto insertAssessment(InsertAssessmentVacancyReqDto data) {

		ConnHandler.begin();

		final AppliedVacancy appliedVacancy = appliedVacancyDao.getByIdRef(data.getAppliedVacancyId());

		final AssessmentVacancy assessmentVacancy = new AssessmentVacancy();
		assessmentVacancy.setAppliedVacancy(appliedVacancy);
		assessmentVacancy.setAssessmentLocation(data.getAssessmentLocation());
		assessmentVacancy.setEndDate(DateUtil.dateParse(data.getEndDate()));
		assessmentVacancy.setIsQuestion(data.getIsQuestion());
		assessmentVacancy.setNotes(data.getNotes());
		assessmentVacancy.setScore(data.getScore());
		assessmentVacancy.setStartDate(DateUtil.dateParse(data.getStartDate()));
		final AssessmentVacancy assessmentVacancyDb = assessmentVacancyDao.saveAndFlush(assessmentVacancy);

		ConnHandler.commit();
		
		sendAssessment(assessmentVacancyDb, appliedVacancy.getCandidate().getCandidateEmail());
		
		final InsertResDto response = new InsertResDto();
		response.setId(assessmentVacancyDb.getId());
		response.setMessage("Assessment has been created!");

		return response;

	}
	
	public void sendAssessment(AssessmentVacancy assessmentVacancyDb, String email) {
		final AssessmentVacancyReqDto assessmentVacancyReqDto = new AssessmentVacancyReqDto();
		
		final AppliedVacancy applied = appliedVacancyDao.getById(assessmentVacancyDb.getAppliedVacancy().getId());
		final JobVacancy job = jobVacancyDao.getById(applied.getJobVacancy().getId());
		
		assessmentVacancyReqDto.setCompanyName(job.getCompany().getCompanyName());
		assessmentVacancyReqDto.setCompanyPhoto(job.getCompany().getPhoto().getFileContent());
		assessmentVacancyReqDto.setVacancyTitle(job.getVacancyTitle());
		assessmentVacancyReqDto.setLevelName(job.getExpLevel().getLevelName());
		assessmentVacancyReqDto.setStartDate(assessmentVacancyDb.getStartDate());
		assessmentVacancyReqDto.setEndDate(assessmentVacancyDb.getEndDate());
		assessmentVacancyReqDto.setNotes(assessmentVacancyDb.getNotes());
		assessmentVacancyReqDto.setAssessmentLocation(assessmentVacancyDb.getAssessmentLocation());
		
		try {				
			final EmailReqDto emailReqDto = new EmailReqDto();
			emailReqDto.setSubject("Your application has assess by company");
			emailReqDto.setEmail(email);
			mailService.sendAssessment(emailReqDto, assessmentVacancyReqDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public AssessmentVacancyResDto getByAppliedId(String appliedVacancyId) {
		final AssessmentVacancy assessmentVacancy = assessmentVacancyDao.getByAppliedVacancyId(appliedVacancyId);
		
		final AssessmentVacancyResDto response = new AssessmentVacancyResDto();
		response.setStartDate(DateUtil.dateFormat(assessmentVacancy.getStartDate()));
		response.setEndDate(DateUtil.dateFormat(assessmentVacancy.getEndDate()));
		response.setIsQuestion(assessmentVacancy.getIsQuestion());
		response.setLocation(assessmentVacancy.getAssessmentLocation());
		response.setNotes(assessmentVacancy.getNotes());
		response.setScore(assessmentVacancy.getScore());
		
		return response;
	}
	
	public DeleteResDto deleteAssessment(String assessementId) {
		try {
			ConnHandler.begin();
			assessmentVacancyDao.deleteById(assessementId);
			ConnHandler.commit();
			final DeleteResDto response = new DeleteResDto();
			response.setMessage("Assessment has been deleted!");
			return response;			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
