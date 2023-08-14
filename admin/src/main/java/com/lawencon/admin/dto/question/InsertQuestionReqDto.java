package com.lawencon.admin.dto.question;

import java.util.List;

import com.lawencon.admin.dto.questionoption.InsertQuestionOptionReqDto;

public class InsertQuestionReqDto {

	private String jobVacancyId;
	private String questionCode;
	private String questionBody;
	private List<InsertQuestionOptionReqDto> options;
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
	public List<InsertQuestionOptionReqDto> getOptions() {
		return options;
	}
	public void setOptions(List<InsertQuestionOptionReqDto> options) {
		this.options = options;
	}
	
	
}
