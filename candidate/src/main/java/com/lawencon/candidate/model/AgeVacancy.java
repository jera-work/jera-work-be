package com.lawencon.candidate.model;

import com.lawencon.base.BaseEntity;

public class AgeVacancy extends BaseEntity {
	private String ageCode;
	private String ageName;
	
	public String getAgeCode() {
		return ageCode;
	}
	public void setAgeCode(String ageCode) {
		this.ageCode = ageCode;
	}
	public String getAgeName() {
		return ageName;
	}
	public void setAgeName(String ageName) {
		this.ageName = ageName;
	}
}
