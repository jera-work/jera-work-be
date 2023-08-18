package com.lawencon.admin.dto.email;

import java.time.LocalDate;

public class OfferingReqDto {
	private String companyName;
	private String companyPhoto;
	private String picHrName;
	private String candidateName;
	private String vacancyTitle;
	private String typeName;
	private String levelName;
	private String salary;
	private LocalDate startDate;
	private LocalDate endDate;
	private String offeringLocation;
	
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
	public String getPicHrName() {
		return picHrName;
	}
	public void setPicHrName(String picHrName) {
		this.picHrName = picHrName;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getVacancyTitle() {
		return vacancyTitle;
	}
	public void setVacancyTitle(String vacancyTitle) {
		this.vacancyTitle = vacancyTitle;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
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
	public String getOfferingLocation() {
		return offeringLocation;
	}
	public void setOfferingLocation(String offeringLocation) {
		this.offeringLocation = offeringLocation;
	}
}
