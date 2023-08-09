package com.lawencon.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_major")
public class Major extends BaseEntity {
	
	@Column(name = "major_code", nullable = false, unique = true, length = 8)
	private String majorCode;
	
	@Column(name = "major_name", nullable = false, length = 30)
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
