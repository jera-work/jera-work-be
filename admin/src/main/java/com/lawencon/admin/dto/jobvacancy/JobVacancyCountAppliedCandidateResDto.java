package com.lawencon.admin.dto.jobvacancy;

public class JobVacancyCountAppliedCandidateResDto {
	private Integer id;
	private String jobName;
	private Long appliedCount;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public Long getAppliedCount() {
		return appliedCount;
	}
	public void setAppliedCount(Long appliedCount) {
		this.appliedCount = appliedCount;
	}
	
}
