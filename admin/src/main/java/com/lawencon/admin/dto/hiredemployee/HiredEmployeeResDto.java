package com.lawencon.admin.dto.hiredemployee;

public class HiredEmployeeResDto {
	
	private String hiredEmployeeId;
	private String candidateName;
	private String companyName;
	private String jobTypeName;
	private String levelName;
	private String vacancyTitle;
	private String appliedId;
	private String createdAt;

	public String getAppliedId() {
		return appliedId;
	}

	public void setAppliedId(String appliedId) {
		this.appliedId = appliedId;
	}

	public String getHiredEmployeeId() {
		return hiredEmployeeId;
	}

	public void setHiredEmployeeId(String hiredEmployeeId) {
		this.hiredEmployeeId = hiredEmployeeId;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getJobTypeName() {
		return jobTypeName;
	}

	public void setJobTypeName(String jobTypeName) {
		this.jobTypeName = jobTypeName;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getVacancyTitle() {
		return vacancyTitle;
	}

	public void setVacancyTitle(String vacancyTitle) {
		this.vacancyTitle = vacancyTitle;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

}
