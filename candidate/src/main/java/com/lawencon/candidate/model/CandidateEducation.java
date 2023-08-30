package com.lawencon.candidate.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_candidate_education")
public class CandidateEducation extends BaseEntity {

	@Column(name = "education_code", length = 5, nullable = false)
	private String educationCode;
	@OneToOne
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;
	@Column(name = "institution_name", length = 50, nullable = false)
	private String institutionName;
	@Column(name = "degree_id", nullable = false)
	private String degree;
	@Column(name = "major_id", nullable = false)
	private String major;
	@Column(name = "gpa", nullable = false)
	private Float gpa;
	@Column(name = "start_year", nullable = false)
	private LocalDateTime startYear;
	@Column(name = "end_year", nullable = false)
	private LocalDateTime endYear;
	@Column(name = "institution_adress", nullable = false)
	private String institutionAddress;

	public String getEducationCode() {
		return educationCode;
	}

	public void setEducationCode(String educationCode) {
		this.educationCode = educationCode;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public Float getGpa() {
		return gpa;
	}

	public void setGpa(Float gpa) {
		this.gpa = gpa;
	}

	public LocalDateTime getStartYear() {
		return startYear;
	}

	public void setStartYear(LocalDateTime startYear) {
		this.startYear = startYear;
	}

	public LocalDateTime getEndYear() {
		return endYear;
	}

	public void setEndYear(LocalDateTime endYear) {
		this.endYear = endYear;
	}

	public String getInstitutionAddress() {
		return institutionAddress;
	}

	public void setInstitutionAddress(String institutionAddress) {
		this.institutionAddress = institutionAddress;
	}

}
