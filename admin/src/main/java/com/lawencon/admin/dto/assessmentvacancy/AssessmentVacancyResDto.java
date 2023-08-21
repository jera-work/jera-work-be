package com.lawencon.admin.dto.assessmentvacancy;

public class AssessmentVacancyResDto {
	private boolean isQuestion;
	private Float score;
	private String notes;
	private String startDate;
	private String endDate;
	private String location;
	public boolean isQuestion() {
		return isQuestion;
	}
	public void setIsQuestion(boolean isQuestion) {
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
