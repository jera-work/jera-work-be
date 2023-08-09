package com.lawencon.candidate.model;

import com.lawencon.base.BaseEntity;

public class Nationality extends BaseEntity {
	private String nationalityCode;
	private String nationalityName;

	public String getNationalityCode() {
		return nationalityCode;
	}

	public void setNationalityCode(String nationalityCode) {
		this.nationalityCode = nationalityCode;
	}

	public String getNationalityName() {
		return nationalityName;
	}

	public void setNationalityName(String nationalityName) {
		this.nationalityName = nationalityName;
	}
}
