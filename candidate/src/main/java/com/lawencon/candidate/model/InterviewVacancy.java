package com.lawencon.candidate.model;

import java.time.LocalDate;

import com.lawencon.base.BaseEntity;

public class InterviewVacancy extends BaseEntity {
	private String appliedVacancy;
	private String notes;
	private LocalDate startDate;
	private LocalDate endDate;
	private String interviewLocation;
	
	public String getAppliedVacancy() {
		return appliedVacancy;
	}
	public void setAppliedVacancy(String appliedVacancy) {
		this.appliedVacancy = appliedVacancy;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
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
	public String getInterviewLocation() {
		return interviewLocation;
	}
	public void setInterviewLocation(String interviewLocation) {
		this.interviewLocation = interviewLocation;
	}
}
