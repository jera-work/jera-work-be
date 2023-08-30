package com.lawencon.candidate.dto.appliedvacancy;

public class AppliedVacancyProgressResDto {

	private String jobVacancyId;
	private String progressCode;
	private String appliedVacancyFromAdminId;

	public String getAppliedVacancyFromAdminId() {
		return appliedVacancyFromAdminId;
	}

	public void setAppliedVacancyFromAdminId(String appliedVacancyFromAdminId) {
		this.appliedVacancyFromAdminId = appliedVacancyFromAdminId;
	}

	public String getJobVacancyId() {
		return jobVacancyId;
	}

	public void setJobVacancyId(String jobVacancyId) {
		this.jobVacancyId = jobVacancyId;
	}

	public String getProgressCode() {
		return progressCode;
	}

	public void setProgressCode(String progressCode) {
		this.progressCode = progressCode;
	}

}
