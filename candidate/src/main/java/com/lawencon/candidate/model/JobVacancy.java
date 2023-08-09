package com.lawencon.candidate.model;

import java.time.LocalDate;

import com.lawencon.base.BaseEntity;

public class JobVacancy extends BaseEntity{
	private String vacancyCode;
	private String vacancyTitle;
	private LocalDate startDate;
	private LocalDate endDate;
	private ExperienceLevel expLevel;
	private AvailableStatus availableStatus;
	private Long candidateTotal;
	private Company company;
	private VacancyDescription vacancyDescription;
	
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
	public ExperienceLevel getExpLevel() {
		return expLevel;
	}
	public void setExpLevel(ExperienceLevel expLevel) {
		this.expLevel = expLevel;
	}
	public AvailableStatus getAvailableStatus() {
		return availableStatus;
	}
	public void setAvailableStatus(AvailableStatus availableStatus) {
		this.availableStatus = availableStatus;
	}
	public Long getCandidateTotal() {
		return candidateTotal;
	}
	public void setCandidateTotal(Long candidateTotal) {
		this.candidateTotal = candidateTotal;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public VacancyDescription getVacancyDescription() {
		return vacancyDescription;
	}
	public void setVacancyDescription(VacancyDescription vacancyDescription) {
		this.vacancyDescription = vacancyDescription;
	}
	
}
