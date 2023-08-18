package com.lawencon.admin.dto.jobvacancy;

public class JobSearchResDto {
	private String vacancyTitle;
	private String companyName;
	private String salary;
	private String degreeName;
	private String jobTypeName;
	private String cityName;
	private String createdAt;
	public String getVacancyTitle() {
		return vacancyTitle;
	}
	public void setVacancyTitle(String vacancyTitle) {
		this.vacancyTitle = vacancyTitle;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getDegreeName() {
		return degreeName;
	}
	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}
	public String getJobTypeName() {
		return jobTypeName;
	}
	public void setJobTypeName(String jobTypeName) {
		this.jobTypeName = jobTypeName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
}
