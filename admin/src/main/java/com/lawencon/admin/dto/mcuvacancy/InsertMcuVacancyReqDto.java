package com.lawencon.admin.dto.mcuvacancy;

public class InsertMcuVacancyReqDto {

	private String appliedVacancyId;
	private String startDate;
	private String endDate;

	public String getAppliedVacancyId() {
		return appliedVacancyId;
	}

	public void setAppliedVacancyId(String appliedVacancyId) {
		this.appliedVacancyId = appliedVacancyId;
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

}
