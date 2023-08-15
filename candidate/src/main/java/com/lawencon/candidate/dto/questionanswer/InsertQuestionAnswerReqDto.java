package com.lawencon.candidate.dto.questionanswer;

public class InsertQuestionAnswerReqDto {
	private String appliedVacancyId;
	private String questionId;
	private String questionOptionId;
	public String getAppliedVacancyId() {
		return appliedVacancyId;
	}
	public void setAppliedVacancyId(String appliedVacancyId) {
		this.appliedVacancyId = appliedVacancyId;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getQuestionOptionId() {
		return questionOptionId;
	}
	public void setQuestionOptionId(String questionOptionId) {
		this.questionOptionId = questionOptionId;
	}
	
}
