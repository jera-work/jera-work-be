package com.lawencon.candidate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_religion")
public class Religion extends BaseEntity{
	
	@Column(name = "religion_code", nullable = false, unique = true, length = 8)
	private String religionCode;
	
	@Column(name = "religion_name", nullable = false, length = 30)
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
