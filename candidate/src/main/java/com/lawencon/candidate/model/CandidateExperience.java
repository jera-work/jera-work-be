package com.lawencon.candidate.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_candidate_experience")
public class CandidateExperience extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "candidate_id", nullable = false)
	private Candidate candidate;
	@Column(name="former_position", nullable=false)
	private String formerPosition;
	@Column(name="former_institution", nullable=false)
	private String formerInstitution;
	@Column(name="former_location", nullable=false)
	private String formerLocation;
	@Column(name="former_jobdesk", nullable=false)
	private String formerJobdesk;
	@Column(name="start_date", nullable=false)
	private LocalDate startDate;
	@Column(name="end_date", nullable=false)
	private LocalDate endDate;

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
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
