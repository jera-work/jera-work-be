package com.lawencon.admin.dto.hiredemployee;

public class InsertHiredEmployeeReqDto {
	private String candidateId;
	private String companyId;
	
	public String getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
}
