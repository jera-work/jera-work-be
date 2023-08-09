package com.lawencon.candidate.model;

import com.lawencon.base.BaseEntity;

public class Major extends BaseEntity {
	private String majorCode;
	private String majorName;
	
	public String getMajorCode() {
		return majorCode;
	}
	public void setMajorCode(String majorCode) {
		this.majorCode = majorCode;
	}
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
}
