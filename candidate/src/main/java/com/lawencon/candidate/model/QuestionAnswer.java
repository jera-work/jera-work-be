package com.lawencon.candidate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_question_answer")
public class QuestionAnswer extends BaseEntity{
	
//	@OneToOne
//	@JoinColumn(name = "applied_vacancy_id", nullable = false)
//	private AppliedVacancy appliedVacancy;
//	
//	
//	@Column(name = "question_option_id", nullable = false)
//	private String questionOptionId;
//	
//	@Column(name = "question_id", nullable = false)
//	private String questionId;
//	
//	public AppliedVacancy getAppliedVacancy() {
//		return appliedVacancy;
//	}
//	public void setAppliedVacancy(AppliedVacancy appliedVacancy) {
//		this.appliedVacancy = appliedVacancy;
//	}
//	public String getQuestionOptionId() {
//		return questionOptionId;
//	}
//	public void setQuestionOptionId(String questionOptionId) {
//		this.questionOptionId = questionOptionId;
//	}
//	public String getQuestionId() {
//		return questionId;
//	}
//	public void setQuestionId(String questionId) {
//		this.questionId = questionId;
//	}
	
}
