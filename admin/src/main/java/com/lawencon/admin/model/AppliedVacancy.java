package com.lawencon.admin.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_applied_vacancy")
public class AppliedVacancy extends BaseEntity {
	
	@OneToOne
	@JoinColumn(name = "candidate_id", nullable = false)
	private Candidate candidate;
	
	@OneToOne
	@JoinColumn(name = "job_vacancy_id", nullable = false)
	private JobVacancy jobVacancy;
	
	@OneToOne
	@JoinColumn(name = "applied_status_id", nullable = false)
	private AppliedStatus appliedStatus;
	
	@OneToOne
	@JoinColumn(name = "applied_progress_id", nullable = false)
	private AppliedProgress appliedProgress;
	
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	public JobVacancy getJobVacancy() {
		return jobVacancy;
	}
	public void setJobVacancy(JobVacancy jobVacancy) {
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
