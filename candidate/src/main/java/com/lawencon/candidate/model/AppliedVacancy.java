package com.lawencon.candidate.model;

import javax.persistence.Column;
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

	@Column(name = "applied_status_id")
	private String appliedStatus;

	@Column(name = "applied_progress_id")
	private String appliedProgress;

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

	public String getAppliedStatus() {
		return appliedStatus;
	}

	public void setAppliedStatus(String appliedStatus) {
		this.appliedStatus = appliedStatus;
	}

	public String getAppliedProgress() {
		return appliedProgress;
	}

	public void setAppliedProgress(String appliedProgress) {
		this.appliedProgress = appliedProgress;
	}

}
