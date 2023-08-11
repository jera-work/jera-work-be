package com.lawencon.admin.dto.offering;

public class InsertOfferingReqDto {
	private String appliedVacancyId;
	private Boolean isApprove;
	private String startDate;
	private String endDate;
	private String description;
	private String offeringLocation;
	
	public String getAppliedVacancyId() {
		return appliedVacancyId;
	}
	public void setAppliedVacancyId(String appliedVacancyId) {
		this.appliedVacancyId = appliedVacancyId;
	}
	public Boolean getIsApprove() {
		return isApprove;
	}
	public void setIsApprove(Boolean isApprove) {
		this.isApprove = isApprove;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
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
