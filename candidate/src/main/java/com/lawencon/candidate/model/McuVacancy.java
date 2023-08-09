package com.lawencon.candidate.model;

import java.time.LocalDate;

import com.lawencon.base.BaseEntity;

public class McuVacancy extends BaseEntity{
	private AppliedVacancy appliedVacancy;
	private File file;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public AppliedVacancy getAppliedVacancy() {
		return appliedVacancy;
	}
	public void setAppliedVacancy(AppliedVacancy appliedVacancy) {
		this.appliedVacancy = appliedVacancy;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
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
