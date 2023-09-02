package com.lawencon.admin.dto.email;

import java.time.LocalDateTime;

public class AssessmentVacancyReqDto {
	private String companyName;
	private String companyPhoto;
	private String vacancyTitle;
	private String levelName;
	private String notes;
	private String startDate;
	private String endDate;
	private String assessmentLocation;
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

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

	public String getAssessmentLocation() {
		return assessmentLocation;
	}

	public void setAssessmentLocation(String assessmentLocation) {
		this.assessmentLocation = assessmentLocation;
	}

}
