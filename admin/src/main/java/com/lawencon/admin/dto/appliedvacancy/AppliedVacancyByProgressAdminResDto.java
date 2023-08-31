package com.lawencon.admin.dto.appliedvacancy;

public class AppliedVacancyByProgressAdminResDto {

	private String progressId;
	private String progressName;
	private String progressCode;
	private Integer appliedCount;
	
	public String getProgressCode() {
		return progressCode;
	}
	public void setProgressCode(String progressCode) {
		this.progressCode = progressCode;
	}
	public String getProgressId() {
		return progressId;
	}
	public void setProgressId(String progressId) {
		this.progressId = progressId;
	}
	public String getProgressName() {
		return progressName;
	}
	public void setProgressName(String progressName) {
		this.progressName = progressName;
	}
	public Integer getAppliedCount() {
		return appliedCount;
	}
	public void setAppliedCount(Integer appliedCount) {
		this.appliedCount = appliedCount;
	}
	
}
