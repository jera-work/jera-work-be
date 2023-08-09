package com.lawencon.candidate.model;

import java.time.LocalDate;

import com.lawencon.base.BaseEntity;

public class CandidateEducation extends BaseEntity{
	private Candidate candidate;
	private String institutionName;
	private Degree degree;
	private Major major;
	private Float gpa;
	private LocalDate startYear;
	private LocalDate endYear;
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
