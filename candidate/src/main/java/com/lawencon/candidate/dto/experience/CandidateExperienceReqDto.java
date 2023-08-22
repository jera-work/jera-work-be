package com.lawencon.candidate.dto.experience;

public class CandidateExperienceReqDto {

	private String candidateEmail;
	private String formerPosition;
	private String formerInstitution;
	private String formerLocation;
	private String formerJobdesc;
	private String startDate;
	private String endDate;

	public String getCandidateEmail() {
		return candidateEmail;
	}

	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}

	public String getFormerPosition() {
		return formerPosition;
	}

	public void setFormerPosition(String formerPosition) {
		this.formerPosition = formerPosition;
	}

	public String getFormerInstitution() {
		return formerInstitution;
	}

	public void setFormerInstitution(String formerInstitution) {
		this.formerInstitution = formerInstitution;
	}

	public String getFormerLocation() {
		return formerLocation;
	}

	public void setFormerLocation(String formerLocation) {
		this.formerLocation = formerLocation;
	}

	public String getFormerJobdesc() {
		return formerJobdesc;
	}

	public void setFormerJobdesc(String formerJobdesc) {
		this.formerJobdesc = formerJobdesc;
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

}
