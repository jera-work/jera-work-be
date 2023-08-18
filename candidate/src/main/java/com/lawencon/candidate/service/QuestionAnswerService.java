package com.lawencon.candidate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.dao.AppliedVacancyDao;
import com.lawencon.candidate.dao.QuestionAnswerDao;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.questionanswer.InsertQuestionAnswerReqDto;
import com.lawencon.candidate.model.AppliedVacancy;
import com.lawencon.candidate.model.QuestionAnswer;

@Service
public class QuestionAnswerService {

	@Autowired
	private ApiService apiService;
	
	@Autowired
	private QuestionAnswerDao questionAnswerDao;
	
	@Autowired
	private AppliedVacancyDao appliedVacancyDao;
	
	public InsertResDto submitAnswer(List<InsertQuestionAnswerReqDto> data) {
		final InsertResDto response = new InsertResDto();
		
		if (data.size() > 0) {
			ConnHandler.begin();
			
			for(int i = 0; i < data.size(); i++) {
				final QuestionAnswer questionAnswer = new QuestionAnswer();
				
				final AppliedVacancy appliedVacancy = appliedVacancyDao.getById(data.get(i).getAppliedVacancyId());
				questionAnswer.setAppliedVacancy(appliedVacancy);
				
				questionAnswer.setQuestionId(data.get(i).getQuestionId());
				questionAnswer.setQuestionOptionId(data.get(i).getQuestionOptionId());
				
				data.get(i).setJobVacancyCode(appliedVacancy.getJobVacancy().getVacancyCode());
				data.get(i).setCandidateEmail(appliedVacancy.getCandidate().getCandidateEmail());
				
				questionAnswerDao.save(questionAnswer);
			}
			
			final HttpStatus adminResponse = apiService.writeTo("http://localhost:8081/answers", data);
			
			if(adminResponse.equals(HttpStatus.CREATED)) {
				response.setMessage("Answer submitted successfully");
				ConnHandler.commit();
			} else {
				ConnHandler.rollback();
			}
		}
		return response;
	}
}
