package com.lawencon.admin.dto.mcuvacancy;

public class InsertMcuVacancyReqDto {
	private String appliedVacancy;
	private String fileContent;
	private String fileExt;
	private String startDate;
	private String endDate;
	
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
