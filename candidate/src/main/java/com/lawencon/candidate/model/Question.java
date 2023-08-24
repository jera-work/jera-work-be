package com.lawencon.candidate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_question")
public class Question extends BaseEntity{
	
	@OneToOne
	@JoinColumn(name = "job_vacancy_id", nullable = false)
	private JobVacancy jobVacancy;
	
	@Column(name = "question_code", nullable = false, unique = true, length = 8)
	private String questionCode;
	
	@Column(name = "question_body", nullable = false)
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
