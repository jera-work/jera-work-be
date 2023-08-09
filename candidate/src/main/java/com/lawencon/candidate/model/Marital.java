package com.lawencon.candidate.model;

import com.lawencon.base.BaseEntity;

public class Marital extends BaseEntity {
	private String martialCode;
	private String maritalName;

	public String getMartialCode() {
		return martialCode;
	}

	public void setMartialCode(String martialCode) {
		this.martialCode = martialCode;
	}

	public String getMaritalName() {
		return maritalName;
	}

	public void setMaritalName(String maritalName) {
		this.maritalName = maritalName;
	}

}
