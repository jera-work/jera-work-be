package com.lawencon.admin.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_question_answer")
public class QuestionAnswer extends BaseEntity{
	
	@OneToOne
	@JoinColumn(name = "applied_vacancy_id", nullable = false)
	private AppliedVacancy appliedVacancy;
	
	@OneToOne
	@JoinColumn(name = "question_option_id", nullable = false)
	private QuestionOption questionOption;
	
	@OneToOne
	@JoinColumn(name = "question_id", nullable = false)
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
