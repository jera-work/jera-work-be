package com.lawencon.admin.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_assessment_vacancy")
public class AssessmentVacancy extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "applied_vacancy_id", nullable = false)
	private AppliedVacancy appliedVacancy;

	@Column(name = "is_question", nullable = true)
	private Boolean isQuestion;

	@Column(name = "score", nullable = true)
	private Float score;

	@Column(name = "notes", nullable = true)
	private String notes;

	@Column(name = "start_date", nullable = false)
	private LocalDateTime startDate;

	@Column(name = "end_date", nullable = false)
	private LocalDateTime endDate;

	@Column(name = "assessment_location", nullable = false)
	private String assessmentLocation;

	public AppliedVacancy getAppliedVacancy() {
		return appliedVacancy;
	}

	public void setAppliedVacancy(AppliedVacancy appliedVacancy) {
		this.appliedVacancy = appliedVacancy;
	}

	public Boolean getIsQuestion() {
		return isQuestion;
	}

	public void setIsQuestion(Boolean isQuestion) {
		this.isQuestion = isQuestion;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public String getAssessmentLocation() {
		return assessmentLocation;
	}

	public void setAssessmentLocation(String assessmentLocation) {
		this.assessmentLocation = assessmentLocation;
	}
}
