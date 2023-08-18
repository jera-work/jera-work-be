package com.lawencon.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.AppliedVacancyDao;
import com.lawencon.admin.dao.AssessmentVacancyDao;
import com.lawencon.admin.dao.CandidateDao;
import com.lawencon.admin.dao.JobVacancyDao;
import com.lawencon.admin.dao.QuestionAnswerDao;
import com.lawencon.admin.dao.QuestionDao;
import com.lawencon.admin.dao.QuestionOptionDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.questionanswer.InsertQuestionAnswerReqDto;
import com.lawencon.admin.model.AppliedVacancy;
import com.lawencon.admin.model.AssessmentVacancy;
import com.lawencon.admin.model.Candidate;
import com.lawencon.admin.model.JobVacancy;
import com.lawencon.admin.model.Question;
import com.lawencon.admin.model.QuestionAnswer;
import com.lawencon.admin.model.QuestionOption;
import com.lawencon.base.ConnHandler;

@Service
public class QuestionAnswerService {
	
	@Autowired
	private QuestionAnswerDao questionAnswerDao;
	
	@Autowired
	private QuestionDao questionDao;
	
	@Autowired
	private QuestionOptionDao questionOptionDao;
	
	@Autowired
	private AppliedVacancyDao appliedVacancyDao;
	
	@Autowired
	private CandidateDao candidateDao;
	
	@Autowired
	private JobVacancyDao jobVacancyDao;
	
	@Autowired
	private AssessmentVacancyDao assessmentVacancyDao;
	
	public InsertResDto submitAnswer(List<InsertQuestionAnswerReqDto> data) {
		final InsertResDto response = new InsertResDto();
		
		if (data.size() > 0) {
			ConnHandler.begin();
			
			for(int i = 0; i < data.size(); i++) {
				final QuestionAnswer questionAnswer = new QuestionAnswer();
				
				final Candidate candidate = candidateDao.getByEmail(data.get(i).getCandidateEmail());
				
				final JobVacancy jobVacancy = jobVacancyDao.getByCode(data.get(i).getJobVacancyCode());
				
				final AppliedVacancy appliedVacancyId = appliedVacancyDao.getByJobVacancyAndCandidate(jobVacancy.getId(), candidate.getId());
				
				final AppliedVacancy appliedVacancy = appliedVacancyDao.getById(appliedVacancyId.getId());
				questionAnswer.setAppliedVacancy(appliedVacancy);
				
				final Question question = questionDao.getByIdRef(data.get(i).getQuestionId());
				questionAnswer.setQuestion(question);
				
				final QuestionOption questionOption = questionOptionDao.getByIdRef(data.get(i).getQuestionOptionId());
				questionAnswer.setQuestionOption(questionOption);
				
				questionAnswerDao.save(questionAnswer);
			}
			
			final AssessmentVacancy assessmentVacancy = assessmentVacancyDao.getById(data.get(0).getAssesmentVacancyId());
			
			final Candidate candidate = candidateDao.getByEmail(data.get(0).getCandidateEmail());
			
			final JobVacancy jobVacancy = jobVacancyDao.getByCode(data.get(0).getJobVacancyCode());
			
			assessmentVacancy.setScore(questionAnswerDao.countScore(jobVacancy.getId(), candidate.getId()));
			
			response.setMessage("Answer submitted successfully");
			ConnHandler.commit();
		}
		return response;
	}
}
