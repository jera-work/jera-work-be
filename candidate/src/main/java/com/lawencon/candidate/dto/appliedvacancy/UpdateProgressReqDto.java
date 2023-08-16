package com.lawencon.candidate.dto.appliedvacancy;

public class UpdateProgressReqDto {
	private String jobVacancyCode;
	private String candidateEmail;
	private String appliedVacancyId;
	private String appliedProgressId;

	public String getJobVacancyCode() {
		return jobVacancyCode;
	}

	public void setJobVacancyCode(String jobVacancyCode) {
		this.jobVacancyCode = jobVacancyCode;
	}

	public String getAppliedVacancyId() {
		return this.appliedVacancyId;
	}

	public void setAppliedVacancyId(String appliedVacancyId) {
		this.appliedVacancyId = appliedVacancyId;
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
	
	
}
