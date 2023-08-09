package com.lawencon.candidate.model;

import com.lawencon.base.BaseEntity;

public class Degree extends BaseEntity{
	private String degreeCode;
	private String degreeName;
	
	public String getDegreeCode() {
		return degreeCode;
	}
	public void setDegreeCode(String degreeCode) {
		this.degreeCode = degreeCode;
	}
	public String getDegreeName() {
		return degreeName;
	}
	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}
}
