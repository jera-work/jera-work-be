package com.lawencon.candidate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name="t_candidate")
public class Candidate extends BaseEntity {
	
	@Column(name="candidate_email", length=30, nullable=false)
	private String candidateEmail;
	@Column(name="candidate_password", length=8, nullable=false)
	private String candidatePassword;
	@OneToOne
	@JoinColumn(name="candidate_profile_id")
	private CandidateProfile candidateProfile;
	
	public String getCandidateEmail() {
		return candidateEmail;
	}
	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}
	public String getCandidatePassword() {
		return candidatePassword;
	}
	public void setCandidatePassword(String candidatePassword) {
		this.candidatePassword = candidatePassword;
	}
	public CandidateProfile getCandidateProfile() {
		return candidateProfile;
	}
	public void setCandidateProfile(CandidateProfile candidateProfile) {
		this.candidateProfile = candidateProfile;
	}
}
