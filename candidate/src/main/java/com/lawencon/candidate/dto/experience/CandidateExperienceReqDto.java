package com.lawencon.candidate.dto.experience;

import java.time.LocalDate;

public class CandidateExperienceReqDto {

	private String formerPosition;
	private String formerInstitution;
	private String formerLocation;
	private String formerJobdesk;
	private LocalDate startDate;
	private LocalDate endDate;

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

	public String getFormerJobdesk() {
		return formerJobdesk;
	}

	public void setFormerJobdesk(String formerJobdesk) {
		this.formerJobdesk = formerJobdesk;
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
