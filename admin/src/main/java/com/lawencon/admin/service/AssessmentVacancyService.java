package com.lawencon.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.AppliedVacancyDao;
import com.lawencon.admin.dao.AssessmentVacancyDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.assessmentvacancy.InsertAssessmentVacancyReqDto;
import com.lawencon.admin.model.AppliedVacancy;
import com.lawencon.admin.model.AssessmentVacancy;
import com.lawencon.base.ConnHandler;

@Service
public class AssessmentVacancyService {

	@Autowired
	private AppliedVacancyDao appliedVacancyDao;
	@Autowired
	private AssessmentVacancyDao assessmentVacancyDao;
	@Autowired
	private SendMailService mailService;

	public InsertResDto insertAssessment(InsertAssessmentVacancyReqDto data) {

		ConnHandler.begin();

		final AppliedVacancy appliedVacancy = appliedVacancyDao.getByIdRef(data.getAppliedVacancyId());

		final AssessmentVacancy assessmentVacancy = new AssessmentVacancy();
		assessmentVacancy.setAppliedVacancy(appliedVacancy);
		assessmentVacancy.setAssessmentLocation(data.getAssessmentLocation());
		assessmentVacancy.setEndDate(data.getEndDate());
		assessmentVacancy.setIsQuestion(data.getIsQuestion());
		assessmentVacancy.setNotes(data.getNotes());
		assessmentVacancy.setScore(data.getScore());
		assessmentVacancy.setStartDate(data.getStartDate());
		final AssessmentVacancy assessmentVacancyDb = assessmentVacancyDao.saveAndFlush(assessmentVacancy);

		ConnHandler.commit();

//		mailService.sendEmail(appliedVacancy.getCandidate().getCandidateEmail());
		final InsertResDto response = new InsertResDto();
		response.setId(assessmentVacancyDb.getId());
		response.setMessage("Assessment has been created!");

		return response;

	}

}
