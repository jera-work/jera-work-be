package com.lawencon.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_vacancy_description")
public class VacancyDescription extends BaseEntity{
	
	@OneToOne
	@JoinColumn(name = "degree_id", nullable = false)
	private Degree degree;
	
	@OneToOne
	@JoinColumn(name = "gender_id", nullable = false)
	private Gender gender;
	
	@OneToOne
	@JoinColumn(name = "age_vacancy_id", nullable = false)
	private AgeVacancy ageVacancy;
	
	@OneToOne
	@JoinColumn(name = "job_type_id", nullable = false)
	private JobType jobType;
	
	@Column(name = "salary", nullable = false)
	private String salary;
	
	@OneToOne
	@JoinColumn(name = "city_id", nullable = false)
	private City city;
	
	@Column(name = "address", nullable = false)
	private String address;
	
	@Column(name = "description", nullable = false)
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
