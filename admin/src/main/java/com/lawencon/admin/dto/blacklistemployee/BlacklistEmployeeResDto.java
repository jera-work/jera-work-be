package com.lawencon.admin.dto.blacklistemployee;

public class BlacklistEmployeeResDto {
	private String blacklistEmployeeId;
	private String candidateId;
	private String candidateName;
	private String companyName;
	public String getBlacklistEmployeeId() {
		return blacklistEmployeeId;
	}
	public void setBlacklistEmployeeId(String blacklistEmployeeId) {
		this.blacklistEmployeeId = blacklistEmployeeId;
	}
	public String getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}
