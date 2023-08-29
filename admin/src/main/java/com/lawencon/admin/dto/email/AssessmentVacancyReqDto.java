package com.lawencon.admin.dto.email;

import java.time.LocalDate;

public class AssessmentVacancyReqDto {
	private String companyName;
	private String companyPhoto;
	private String vacancyTitle;
	private String levelName;
	private String notes;
	private LocalDate startDate;
	private LocalDate endDate;
	private String assessmentLocation;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyPhoto() {
		return companyPhoto;
	}
	public void setCompanyPhoto(String companyPhoto) {
		this.companyPhoto = companyPhoto;
	}
	public String getVacancyTitle() {
		return vacancyTitle;
	}
	public void setVacancyTitle(String vacancyTitle) {
		this.vacancyTitle = vacancyTitle;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
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
