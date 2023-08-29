package com.lawencon.admin.dto.blacklistemployee;

public class BlacklistEmployeeResDto {
	private String blacklistEmployeeId;
	private String candidateId;
	private String candidateName;
	private String jobTypeName;
	private String levelName;
	private String vacancyTitle;
	private String createdAt;
	public String getBlacklistEmployeeId() {
		return blacklistEmployeeId;
	}
	public void setBlacklistEmployeeId(String blacklistEmployeeId) {
		this.blacklistEmployeeId = blacklistEmployeeId;
	}
	public String getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
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
