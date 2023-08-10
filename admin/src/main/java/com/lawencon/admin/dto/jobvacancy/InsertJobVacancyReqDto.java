package com.lawencon.admin.dto.jobvacancy;

import java.time.LocalDate;

import com.lawencon.admin.dto.vacancydescription.InsertVacancyDescriptionReqDto;

public class InsertJobVacancyReqDto {
	private String vacancyCode;
	private String vacancyTitle;
	private String picUserId;
	private String picHrId;
	private LocalDate startDate;
	private LocalDate endDate;
	private String expLevelId;
	private String availableStatusId;
	private String companyId;
	
	private InsertVacancyDescriptionReqDto vacancyDescription;

	public String getVacancyCode() {
		return vacancyCode;
	}
	public void setVacancyCode(String vacancyCode) {
		this.vacancyCode = vacancyCode;
	}
	public String getVacancyTitle() {
		return vacancyTitle;
	}
	public void setVacancyTitle(String vacancyTitle) {
		this.vacancyTitle = vacancyTitle;
	}
	public String getPicUserId() {
		return picUserId;
	}
	public void setPicUserId(String picUserId) {
		this.picUserId = picUserId;
	}
	public String getPicHrId() {
		return picHrId;
	}
	public void setPicHrId(String picHrId) {
		this.picHrId = picHrId;
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
	public String getExpLevelId() {
		return expLevelId;
	}
	public void setExpLevelId(String expLevelId) {
		this.expLevelId = expLevelId;
	}
	public String getAvailableStatusId() {
		return availableStatusId;
	}
	public void setAvailableStatusId(String availableStatusId) {
		this.availableStatusId = availableStatusId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public InsertVacancyDescriptionReqDto getVacancyDescription() {
		return vacancyDescription;
	}
	public void setVacancyDescription(InsertVacancyDescriptionReqDto vacancyDescription) {
		this.vacancyDescription = vacancyDescription;
	}
	
}
