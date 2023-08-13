package com.lawencon.candidate.dto.vacancydescription;

public class InsertVacancyDescriptionReqDto {
	private String degreeId;
	private String genderId;
	private String ageVacancyId;
	private String jobTypeId;
	private String salary;
	private String cityId;
	private String address;
	private String description;
	
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
