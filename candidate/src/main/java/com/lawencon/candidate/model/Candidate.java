package com.lawencon.candidate.model;

import com.lawencon.base.BaseEntity;

public class Candidate extends BaseEntity {
	private String candidateEmail;
	private String candidatePassword;
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
