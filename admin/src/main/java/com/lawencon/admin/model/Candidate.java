package com.lawencon.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_candidate")
public class Candidate extends BaseEntity {

	@Column(name = "candidate_email", nullable = false, unique = true, length = 30)
	private String candidateEmail;

	@Column(name = "candidate_code", length = 5, nullable = false)
	private String candidateCode;

	@OneToOne
	@JoinColumn(name = "candidate_profile_id", nullable = false)
	private CandidateProfile candidateProfile;

	public String getCandidateCode() {
		return candidateCode;
	}

	public void setCandidateCode(String candidateCode) {
		this.candidateCode = candidateCode;
	}

	public String getCandidateEmail() {
		return candidateEmail;
	}

	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}

	public CandidateProfile getCandidateProfile() {
		return candidateProfile;
	}

	public void setCandidateProfile(CandidateProfile candidateProfile) {
		this.candidateProfile = candidateProfile;
	}
}
