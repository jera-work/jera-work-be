package com.lawencon.candidate.dto.appliedstatus;

public class UpdateStatusReqDto {

	private String jobVacancyCode;
	private String candidateEmail;
	private String appliedVacancyId;
	private String appliedStatusId;

	public String getJobVacancyCode() {
		return jobVacancyCode;
	}

	public void setJobVacancyCode(String jobVacancyCode) {
		this.jobVacancyCode = jobVacancyCode;
	}

	public String getCandidateEmail() {
		return candidateEmail;
	}

	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}

	public String getAppliedVacancyId() {
		return appliedVacancyId;
	}

	public void setAppliedVacancyId(String appliedVacancyId) {
		this.appliedVacancyId = appliedVacancyId;
	}

	public String getAppliedStatusId() {
		return appliedStatusId;
	}

	public void setAppliedStatusId(String appliedStatusId) {
		this.appliedStatusId = appliedStatusId;
	}

}
