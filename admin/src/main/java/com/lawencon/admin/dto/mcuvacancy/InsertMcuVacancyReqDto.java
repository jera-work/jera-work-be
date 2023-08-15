package com.lawencon.admin.dto.mcuvacancy;

import java.time.LocalDate;

public class InsertMcuVacancyReqDto {

	private String appliedVacancy;
	private String fileContent;
	private String fileExt;
	private LocalDate startDate;
	private LocalDate endDate;

	public String getAppliedVacancy() {
		return appliedVacancy;
	}

	public void setAppliedVacancy(String appliedVacancy) {
		this.appliedVacancy = appliedVacancy;
	}

	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
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
