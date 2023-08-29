package com.lawencon.candidate.dto.questionanswer;

public class InsertQuestionAnswerReqDto {
	private String appliedVacancyId;
	private String jobVacancyCode;
	private String jobVacancyId;
	private String candidateEmail;
	private String questionId;
	private String questionOptionId;
	private String assesmentVacancyId;

	public String getJobVacancyId() {
		return jobVacancyId;
	}

	public void setJobVacancyId(String jobVacancyId) {
		this.jobVacancyId = jobVacancyId;
	}

	public String getJobVacancyCode() {
		return jobVacancyCode;
	}

	public void setJobVacancyCode(String jobVacancyCode) {
		this.jobVacancyCode = jobVacancyCode;
	}

	public String getCandidateEmail() {
		return candidateEmail;
	}

	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}

	public String getAppliedVacancyId() {
		return appliedVacancyId;
	}

	public void setAppliedVacancyId(String appliedVacancyId) {
		this.appliedVacancyId = appliedVacancyId;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getQuestionOptionId() {
		return questionOptionId;
	}

	public void setQuestionOptionId(String questionOptionId) {
		this.questionOptionId = questionOptionId;
	}

	public String getAssesmentVacancyId() {
		return assesmentVacancyId;
	}

	public void setAssesmentVacancyId(String assesmentVacancyId) {
		this.assesmentVacancyId = assesmentVacancyId;
	}

}
