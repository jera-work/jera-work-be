package com.lawencon.candidate.model;

import com.lawencon.base.BaseEntity;

public class QuestionAnswer extends BaseEntity{
	private AppliedVacancy appliedVacancy;
	private QuestionOption questionOption;
	private Question question;
	
	public AppliedVacancy getAppliedVacancy() {
		return appliedVacancy;
	}
	public void setAppliedVacancy(AppliedVacancy appliedVacancy) {
		this.appliedVacancy = appliedVacancy;
	}
	public QuestionOption getQuestionOption() {
		return questionOption;
	}
	public void setQuestionOption(QuestionOption questionOption) {
		this.questionOption = questionOption;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
}
