package com.lawencon.candidate.model;

import com.lawencon.base.BaseEntity;

public class QuestionOption extends BaseEntity{
	private String optionLabel;
	private Boolean isCorrect;
	private Question question;
	
	public String getOptionLabel() {
		return optionLabel;
	}
	public void setOptionLabel(String optionLabel) {
		this.optionLabel = optionLabel;
	}
	public Boolean getIsCorrect() {
		return isCorrect;
	}
	public void setIsCorrect(Boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
}
