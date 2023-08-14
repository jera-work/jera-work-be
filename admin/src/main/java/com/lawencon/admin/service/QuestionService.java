package com.lawencon.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.JobVacancyDao;
import com.lawencon.admin.dao.QuestionDao;
import com.lawencon.admin.dao.QuestionOptionDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.question.InsertQuestionReqDto;
import com.lawencon.admin.model.JobVacancy;
import com.lawencon.admin.model.Question;
import com.lawencon.admin.model.QuestionOption;
import com.lawencon.base.ConnHandler;

@Service
public class QuestionService {

	@Autowired
	private JobVacancyDao jobVacancyDao;
	
	@Autowired
	private QuestionDao questionDao;
	
	@Autowired
	private QuestionOptionDao questionOptionDao;
	
	public InsertResDto createQuestion(InsertQuestionReqDto data) {
		final InsertResDto response = new InsertResDto();
		
		ConnHandler.begin();
		try {
			final Question question = new Question();
			question.setQuestionCode(data.getQuestionCode());
			question.setQuestionBody(data.getQuestionBody());
			
			final JobVacancy jobVacancy = jobVacancyDao.getByIdRef(data.getJobVacancyId());
			question.setJobVacancy(jobVacancy);
			
			final Question insertedQuestion = questionDao.saveAndFlush(question);
			
			if(data.getOptions().size() > 0) {
				for(int i = 0; i < data.getOptions().size(); i++) {
					final QuestionOption questionOption = new QuestionOption();
					
					questionOption.setQuestion(insertedQuestion);
					questionOption.setOptionLabel(data.getOptions().get(i).getOptionLabel());
					questionOption.setIsCorrect(data.getOptions().get(i).getIsCorrect());
					
					questionOptionDao.save(questionOption);
				}
			}
			
			response.setMessage("Question created successfully");
			ConnHandler.commit();
		} catch (Exception e) {
			ConnHandler.rollback();
		}
		
		return response;
	}
}
