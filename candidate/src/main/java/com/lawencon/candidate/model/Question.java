package com.lawencon.candidate.model;

import com.lawencon.base.BaseEntity;

public class Question extends BaseEntity{
	private JobVacancy jobVacancy;
	private String questionCode;
	private String questionBody;
	
	public JobVacancy getJobVacancy() {
		return jobVacancy;
	}
	public void setJobVacancy(JobVacancy jobVacancy) {
		this.jobVacancy = jobVacancy;
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
}
