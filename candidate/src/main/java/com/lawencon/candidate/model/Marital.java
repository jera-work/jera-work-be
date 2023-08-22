package com.lawencon.candidate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_marital")
public class Marital extends BaseEntity{
	
	@Column(name = "marital_code", nullable = false, unique = true, length = 8)
	private String martialCode;
	
	@Column(name = "marital_name", nullable = false, length = 30)
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
