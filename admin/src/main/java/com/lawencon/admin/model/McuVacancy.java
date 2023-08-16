package com.lawencon.admin.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_mcu_vacancy")
public class McuVacancy extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "applied_vacancy_id", nullable = false)
	private AppliedVacancy appliedVacancy;

	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;

	@Column(name = "end_date", nullable = false)
	private LocalDate endDate;

	public AppliedVacancy getAppliedVacancy() {
		return appliedVacancy;
	}

	public void setAppliedVacancy(AppliedVacancy appliedVacancy) {
		this.appliedVacancy = appliedVacancy;
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

}
