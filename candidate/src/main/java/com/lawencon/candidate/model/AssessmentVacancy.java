package com.lawencon.candidate.model;

import java.time.LocalDate;

import com.lawencon.base.BaseEntity;

public class AssessmentVacancy extends BaseEntity{
	private AppliedVacancy appliedVacancy;
	private Boolean isQuestion;
	private Float score;
	private String notes;
	private LocalDate startDate;
	private LocalDate endDate;
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
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public String getAssessmentLocation() {
		return assessmentLocation;
	}
	public void setAssessmentLocation(String assessmentLocation) {
		this.assessmentLocation = assessmentLocation;
	}
}
