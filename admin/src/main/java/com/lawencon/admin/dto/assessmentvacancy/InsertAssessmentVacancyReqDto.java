package com.lawencon.admin.dto.assessmentvacancy;

public class InsertAssessmentVacancyReqDto {

	private String appliedVacancyId;
	private Boolean isQuestion;
	private Float score;
	private String notes;
	private String startDate;
	private String endDate;
	private String assessmentLocation;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getAppliedVacancyId() {
		return appliedVacancyId;
	}

	public void setAppliedVacancyId(String appliedVacancyId) {
		this.appliedVacancyId = appliedVacancyId;
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

	public String getAssessmentLocation() {
		return assessmentLocation;
	}

	public void setAssessmentLocation(String assessmentLocation) {
		this.assessmentLocation = assessmentLocation;
	}
}
