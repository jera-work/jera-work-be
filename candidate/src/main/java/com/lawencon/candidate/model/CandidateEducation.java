package com.lawencon.candidate.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name="t_candidate_education")
public class CandidateEducation extends BaseEntity {
	
	@OneToOne
	@JoinColumn(name="candidate_id")
	private Candidate candidate;
	@Column(name="institution_name", length=50, nullable=false)
	private String institutionName;
	@OneToOne
	@JoinColumn(name="degree_id")
	private Degree degree;
	@OneToOne
	@JoinColumn(name="major_id")
	private Major major;
	@Column(name="gpa", nullable=false)
	private Float gpa;
	@Column(name="start_year", nullable=false)
	private LocalDate startYear;
	@Column(name="end_year", nullable=false)
	private LocalDate endYear;
	@Column(name="institution_", nullable=false)
	private String institutionAddress;

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

	public Degree getDegree() {
		return degree;
	}

	public void setDegree(Degree degree) {
		this.degree = degree;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public Float getGpa() {
		return gpa;
	}

	public void setGpa(Float gpa) {
		this.gpa = gpa;
	}

	public LocalDate getStartYear() {
		return startYear;
	}

	public void setStartYear(LocalDate startYear) {
		this.startYear = startYear;
	}

	public LocalDate getEndYear() {
		return endYear;
	}

	public void setEndYear(LocalDate endYear) {
		this.endYear = endYear;
	}

	public String getInstitutionAddress() {
		return institutionAddress;
	}

	public void setInstitutionAddress(String institutionAddress) {
		this.institutionAddress = institutionAddress;
	}

}
