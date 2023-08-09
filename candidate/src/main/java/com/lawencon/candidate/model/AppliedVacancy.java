package com.lawencon.candidate.model;

import com.lawencon.base.BaseEntity;

public class AppliedVacancy extends BaseEntity {
	private Candidate candidate;
	private String jobVacancy;
	private AppliedStatus appliedStatus;
	private AppliedProgress appliedProgress;
	
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	public String getJobVacancy() {
		return jobVacancy;
	}
	public void setJobVacancy(String jobVacancy) {
		this.jobVacancy = jobVacancy;
	}
	public AppliedStatus getAppliedStatus() {
		return appliedStatus;
	}
	public void setAppliedStatus(AppliedStatus appliedStatus) {
		this.appliedStatus = appliedStatus;
	}
	public AppliedProgress getAppliedProgress() {
		return appliedProgress;
	}
	public void setAppliedProgress(AppliedProgress appliedProgress) {
		this.appliedProgress = appliedProgress;
	}
}
