package com.lawencon.candidate.model;

import java.time.LocalDate;

import com.lawencon.base.BaseEntity;

public class Offering extends BaseEntity{
	private AppliedVacancy appliedVacancy;
	private Boolean isApprove;
	private LocalDate startDate;
	private LocalDate endDate;
	private String description;
	private String offeringLocation;
	
	public AppliedVacancy getAppliedVacancy() {
		return appliedVacancy;
	}
	public void setAppliedVacancy(AppliedVacancy appliedVacancy) {
		this.appliedVacancy = appliedVacancy;
	}
	public Boolean getIsApprove() {
		return isApprove;
	}
	public void setIsApprove(Boolean isApprove) {
		this.isApprove = isApprove;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOfferingLocation() {
		return offeringLocation;
	}
	public void setOfferingLocation(String offeringLocation) {
		this.offeringLocation = offeringLocation;
	}
}
