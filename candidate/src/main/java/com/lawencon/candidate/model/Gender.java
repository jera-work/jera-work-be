package com.lawencon.candidate.model;

import com.lawencon.base.BaseEntity;

public class Gender extends BaseEntity{
	private String genderCode;
	private String genderName;
	public String getGenderCode() {
		return genderCode;
	}
	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}
	public String getGenderName() {
		return genderName;
	}
	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}
}
