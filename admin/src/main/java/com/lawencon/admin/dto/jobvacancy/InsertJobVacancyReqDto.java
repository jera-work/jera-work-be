package com.lawencon.admin.dto.jobvacancy;

import java.time.LocalDate;

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

	private String degreeId;
	private String genderId;
	private String ageVacancyId;
	private String jobTypeId;
	private String salary;
	private String cityId;
	private String address;
	private String description;

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

	public String getDegreeId() {
		return degreeId;
	}

	public void setDegreeId(String degreeId) {
		this.degreeId = degreeId;
	}

	public String getGenderId() {
		return genderId;
	}

	public void setGenderId(String genderId) {
		this.genderId = genderId;
	}

	public String getAgeVacancyId() {
		return ageVacancyId;
	}

	public void setAgeVacancyId(String ageVacancyId) {
		this.ageVacancyId = ageVacancyId;
	}

	public String getJobTypeId() {
		return jobTypeId;
	}

	public void setJobTypeId(String jobTypeId) {
		this.jobTypeId = jobTypeId;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
