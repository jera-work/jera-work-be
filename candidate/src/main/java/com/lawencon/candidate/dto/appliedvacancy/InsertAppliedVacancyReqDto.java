package com.lawencon.candidate.dto.appliedvacancy;

public class InsertAppliedVacancyReqDto {

	private String candidateId;
	private String candidateEmail;
	private String jobVacancyId;
	private String jobVacancyCode;
	private String appliedStatusId;
	private String appliedStatusCode;
	private String appliedProgressId;
	private String appliedProgressCode;

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

	public String getCandidateEmail() {
		return candidateEmail;
	}

	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}

	public String getJobVacancyCode() {
		return jobVacancyCode;
	}

	public void setJobVacancyCode(String jobVacancyCode) {
		this.jobVacancyCode = jobVacancyCode;
	}

	public String getAppliedStatusCode() {
		return appliedStatusCode;
	}

	public void setAppliedStatusCode(String appliedStatusCode) {
		this.appliedStatusCode = appliedStatusCode;
	}

	public String getAppliedProgressCode() {
		return appliedProgressCode;
	}

	public void setAppliedProgressCode(String appliedProgressCode) {
		this.appliedProgressCode = appliedProgressCode;
	}

}
