package com.lawencon.candidate.model;

import com.lawencon.base.BaseEntity;

public class Religion extends BaseEntity {
	private String religionCode;
	private String religionName;

	public String getReligionCode() {
		return religionCode;
	}

	public void setReligionCode(String religionCode) {
		this.religionCode = religionCode;
	}

	public String getReligionName() {
		return religionName;
	}

	public void setReligionName(String religionName) {
		this.religionName = religionName;
	}
}
