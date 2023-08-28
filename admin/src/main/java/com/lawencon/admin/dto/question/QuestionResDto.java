package com.lawencon.admin.dto.question;

import java.util.List;

import com.lawencon.admin.dto.questionoption.QuestionOptionResDto;

public class QuestionResDto {

	private String id;
	private String jobVacancyId;
	private String questionCode;
	private String questionBody;
	private List<QuestionOptionResDto> options;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJobVacancyId() {
		return jobVacancyId;
	}

	public void setJobVacancyId(String jobVacancyId) {
		this.jobVacancyId = jobVacancyId;
	}

	public String getQuestionCode() {
		return questionCode;
	}

	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}

	public String getQuestionBody() {
		return questionBody;
	}

	public void setQuestionBody(String questionBody) {
		this.questionBody = questionBody;
	}

	public List<QuestionOptionResDto> getOptions() {
		return options;
	}

	public void setOptions(List<QuestionOptionResDto> options) {
		this.options = options;
	}

}
