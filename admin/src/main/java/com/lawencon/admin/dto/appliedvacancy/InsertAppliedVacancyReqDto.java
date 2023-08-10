package com.lawencon.admin.dto.appliedvacancy;

public class InsertAppliedVacancyReqDto {
	private String candidateId;
	private String jobVacancyId;
	private String appliedStatusId;
	private String appliedProgressId;
	
	public String getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}
	public String getJobVacancyId() {
		return jobVacancyId;
	}
	public void setJobVacancyId(String jobVacancyId) {
		this.jobVacancyId = jobVacancyId;
	}
	public String getAppliedStatusId() {
		return appliedStatusId;
	}
	public void setAppliedStatusId(String appliedStatusId) {
		this.appliedStatusId = appliedStatusId;
	}
	public String getAppliedProgressId() {
		return appliedProgressId;
	}
	public void setAppliedProgressId(String appliedProgressId) {
		this.appliedProgressId = appliedProgressId;
	}
}
