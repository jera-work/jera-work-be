package com.lawencon.admin.dto.appliedvacancy;

public class AppliedVacancyResDto {
	private String id;
	private String jobVacancyId;
	private String jobVacancyCode;
	private String appliedStatusName;
	private String appliedProgressName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJobVacancyId() {
		return jobVacancyId;
	}
	public void setJobVacancyId(String jobVacancyId) {
		this.jobVacancyId = jobVacancyId;
	}
	public String getJobVacancyCode() {
		return jobVacancyCode;
	}
	public void setJobVacancyCode(String jobVacancyCode) {
		this.jobVacancyCode = jobVacancyCode;
	}
	public String getAppliedStatusName() {
		return appliedStatusName;
	}
	public void setAppliedStatusName(String appliedStatusName) {
		this.appliedStatusName = appliedStatusName;
	}
	public String getAppliedProgressName() {
		return appliedProgressName;
	}
	public void setAppliedProgressName(String appliedProgressName) {
		this.appliedProgressName = appliedProgressName;
	}
	
}
