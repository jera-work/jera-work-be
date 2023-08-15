package com.lawencon.candidate.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_job_vacancy")
public class JobVacancy extends BaseEntity {

	@Column(name = "vacancy_code", nullable = false, unique = true, length = 8)
	private String vacancyCode;

	@Column(name = "vacancy_title", nullable = false, length = 36)
	private String vacancyTitle;

	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;

	@Column(name = "end_date", nullable = false)
	private LocalDate endDate;

	@Column(name = "exp_level_id", nullable = false)
	private String expLevel;

	@Column(name = "available_status_id", nullable = false)
	private String availableStatus;

	@Column(name = "candidate_total", nullable = false)
	private Long candidateTotal;

	@Column(name = "company_id", nullable = false)
	private String company;

	@Column(name = "vacancy_description_id", nullable = false)
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

	public Long getCandidateTotal() {
		return candidateTotal;
	}

	public void setCandidateTotal(Long candidateTotal) {
		this.candidateTotal = candidateTotal;
	}

	public String getExpLevel() {
		return expLevel;
	}

	public void setExpLevel(String expLevel) {
		this.expLevel = expLevel;
	}

	public String getAvailableStatus() {
		return availableStatus;
	}

	public void setAvailableStatus(String availableStatus) {
		this.availableStatus = availableStatus;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public VacancyDescription getVacancyDescription() {
		return vacancyDescription;
	}

	public void setVacancyDescription(VacancyDescription vacancyDescription) {
		this.vacancyDescription = vacancyDescription;
	}

}
