package com.lawencon.admin.dto.mcuvacancy;

import java.time.LocalDate;

public class InsertMcuVacancyReqDto {

	private String appliedVacancy;
	private LocalDate startDate;
	private LocalDate endDate;

	public String getAppliedVacancy() {
		return appliedVacancy;
	}

	public void setAppliedVacancy(String appliedVacancy) {
		this.appliedVacancy = appliedVacancy;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

}
