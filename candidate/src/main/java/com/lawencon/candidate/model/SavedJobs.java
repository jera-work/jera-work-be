package com.lawencon.candidate.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_saved_jobs")
public class SavedJobs extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "candidate_id", nullable = false)
	private Candidate candidate;

	@OneToOne
	@JoinColumn(name = "job_vacancy_id", nullable = false)
	private JobVacancy jobVacancy;

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
}
