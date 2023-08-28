package com.lawencon.admin.dto.email;

import java.time.LocalDate;

public class McuVacancyReqDto {
	private String companyName;
	private String companyPhoto;
	private String vacancyTitle;
	private String levelName;
	private LocalDate startDate;
	private LocalDate endDate;
	private String address;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyPhoto() {
		return companyPhoto;
	}
	public void setCompanyPhoto(String companyPhoto) {
		this.companyPhoto = companyPhoto;
	}
	public String getVacancyTitle() {
		return vacancyTitle;
	}
	public void setVacancyTitle(String vacancyTitle) {
		this.vacancyTitle = vacancyTitle;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
