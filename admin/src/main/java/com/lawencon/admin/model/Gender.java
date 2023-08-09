package com.lawencon.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_gender")
public class Gender extends BaseEntity{
	
	@Column(name = "gender_code", nullable = false, unique = true, length = 8)
	private String genderCode;
	
	@Column(name = "gender_name", nullable = false, length = 30)
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
