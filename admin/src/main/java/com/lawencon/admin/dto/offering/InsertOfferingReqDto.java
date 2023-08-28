package com.lawencon.admin.dto.offering;

public class InsertOfferingReqDto {

	private String appliedVacancyId;
	private Boolean isApprove;
	private String startDate;
	private String endDate;
	private String description;
	private String offeringLocation;
	
	private String companyDescription;
	private String companyDescriptionFontColor;
	private String companyNameFontColor;
	private String benefit;
	private String startWork;

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

	public String getCompanyDescription() {
		return companyDescription;
	}

	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}

	public String getCompanyDescriptionFontColor() {
		return companyDescriptionFontColor;
	}

	public void setCompanyDescriptionFontColor(String companyDescriptionFontColor) {
		this.companyDescriptionFontColor = companyDescriptionFontColor;
	}

	public String getCompanyNameFontColor() {
		return companyNameFontColor;
	}

	public void setCompanyNameFontColor(String companyNameFontColor) {
		this.companyNameFontColor = companyNameFontColor;
	}

	public String getBenefit() {
		return benefit;
	}

	public void setBenefit(String benefit) {
		this.benefit = benefit;
	}

	public String getStartWork() {
		return startWork;
	}

	public void setStartWork(String startWork) {
		this.startWork = startWork;
	}

}
