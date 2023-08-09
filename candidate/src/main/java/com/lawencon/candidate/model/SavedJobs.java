package com.lawencon.candidate.model;

public class SavedJobs {
	private Candidate candidate;
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
