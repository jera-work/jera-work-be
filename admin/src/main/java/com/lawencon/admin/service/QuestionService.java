package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.JobVacancyDao;
import com.lawencon.admin.dao.QuestionDao;
import com.lawencon.admin.dao.QuestionOptionDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.question.InsertQuestionReqDto;
import com.lawencon.admin.dto.question.QuestionResDto;
import com.lawencon.admin.dto.questionoption.QuestionOptionResDto;
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
			if (data.size() > 0) {
				for (int i = 0; i < data.size(); i++) {
					final Question question = new Question();
					question.setQuestionCode(data.get(i).getQuestionCode());
					question.setQuestionBody(data.get(i).getQuestionBody());

					final JobVacancy jobVacancy = jobVacancyDao.getByIdRef(data.get(i).getJobVacancyId());
					question.setJobVacancy(jobVacancy);

					final Question insertedQuestion = questionDao.saveAndFlush(question);

					if (data.get(i).getOptions().size() > 0) {
						for (int j = 0; j < data.get(i).getOptions().size(); j++) {
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

	public List<QuestionResDto> getQuestions(String jobId) {
		final List<QuestionResDto> responses = new ArrayList<>();
		final List<Question> questions = questionDao.getByJobId(jobId);
		
		for (Question question : questions) {
			final QuestionResDto response = new QuestionResDto();
			final List<QuestionOptionResDto> optionResponses = new ArrayList<>();
			
			final List<QuestionOption> options = questionOptionDao.getByQuestionId(question.getId());
			options.forEach(o -> {
				final QuestionOptionResDto optionResponse = new QuestionOptionResDto();
				optionResponse.setOptionId(o.getId());
				optionResponse.setIsCorrect(o.getIsCorrect());
				optionResponse.setOptionLabel(o.getOptionLabel());
				optionResponses.add(optionResponse);
			});
			
//			for (QuestionOption option : options) {
//				final QuestionOptionResDto optionResponse = new QuestionOptionResDto();
//				optionResponse.setOptionId(option.getId());
//				optionResponse.setIsCorrect(option.getIsCorrect());
//				optionResponse.setOptionLabel(option.getOptionLabel());
//				optionResponses.add(optionResponse);
//			}
			
			response.setId(question.getId());
			response.setJobVacancyId(question.getJobVacancy().getId());
			response.setOptions(optionResponses);
			response.setQuestionBody(question.getQuestionBody());
			response.setQuestionCode(question.getQuestionCode());
			responses.add(response);
		}
		
		return responses;
	}
}
