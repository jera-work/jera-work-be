package com.lawencon.candidate.model;

import com.lawencon.base.BaseEntity;

public class VacancyDescription extends BaseEntity{
	private Degree degree;
	private Gender gender;
	private AgeVacancy ageVacancy;
	private JobType jobType;
	private String salary;
	private City city;
	private String address;
	private String description;
	
	public Degree getDegree() {
		return degree;
	}
	public void setDegree(Degree degree) {
		this.degree = degree;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public AgeVacancy getAgeVacancy() {
		return ageVacancy;
	}
	public void setAgeVacancy(AgeVacancy ageVacancy) {
		this.ageVacancy = ageVacancy;
	}
	public JobType getJobType() {
		return jobType;
	}
	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
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
