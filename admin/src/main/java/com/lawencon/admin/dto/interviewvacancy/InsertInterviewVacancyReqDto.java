package com.lawencon.admin.dto.interviewvacancy;

public class InsertInterviewVacancyReqDto {
	private String appliedVacancyId;
	private String notes;
	private String startDate;
	private String endDate;
	private String interviewLocation;
	
	public String getAppliedVacancyId() {
		return appliedVacancyId;
	}
	public void setAppliedVacancyId(String appliedVacancyId) {
		this.appliedVacancyId = appliedVacancyId;
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
	public String getInterviewLocation() {
		return interviewLocation;
	}
	public void setInterviewLocation(String interviewLocation) {
		this.interviewLocation = interviewLocation;
	}
	
}
