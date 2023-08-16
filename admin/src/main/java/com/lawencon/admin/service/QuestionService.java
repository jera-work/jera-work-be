package com.lawencon.admin.service;

import java.util.List;

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
	
	public InsertResDto createQuestion(List<InsertQuestionReqDto> data) {
		final InsertResDto response = new InsertResDto();
		
		ConnHandler.begin();
		try {
			if(data.size() > 0) {
				for(int i = 0; i < data.size(); i++) {
					final Question question = new Question();
					question.setQuestionCode(data.get(i).getQuestionCode());
					question.setQuestionBody(data.get(i).getQuestionBody());
					
					final JobVacancy jobVacancy = jobVacancyDao.getByIdRef(data.get(i).getJobVacancyId());
					question.setJobVacancy(jobVacancy);
					
					final Question insertedQuestion = questionDao.saveAndFlush(question);
					
					if(data.get(i).getOptions().size() > 0) {
						for(int j = 0; j < data.get(i).getOptions().size(); j++) {
							final QuestionOption questionOption = new QuestionOption();
							
							questionOption.setQuestion(insertedQuestion);
							questionOption.setOptionLabel(data.get(i).getOptions().get(j).getOptionLabel());
							questionOption.setIsCorrect(data.get(i).getOptions().get(j).getIsCorrect());
							
							questionOptionDao.save(questionOption);
						}
					}
				}
			}
			
			response.setMessage("Question created successfully");
			ConnHandler.commit();
		} catch (Exception e) {
			e.printStackTrace();
			ConnHandler.rollback();
		}
		
		return response;
	}
}
