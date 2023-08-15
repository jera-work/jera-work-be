package com.lawencon.candidate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_vacancy_description")
public class VacancyDescription extends BaseEntity {

	@Column(name = "degree_id", nullable = false)
	private String degree;

	@Column(name = "gender_id", nullable = false)
	private String gender;

	@Column(name = "age_vacancy_id", nullable = false)
	private String ageVacancy;

	@Column(name = "job_type_id", nullable = false)
	private String jobType;

	@Column(name = "salary", nullable = false)
	private String salary;

	@Column(name = "city_id", nullable = false)
	private String city;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "description", nullable = false)
	private String description;

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAgeVacancy() {
		return ageVacancy;
	}

	public void setAgeVacancy(String ageVacancy) {
		this.ageVacancy = ageVacancy;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
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
