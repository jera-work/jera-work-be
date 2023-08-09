package com.lawencon.candidate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_applied_status")
public class AppliedStatus extends BaseEntity{
	
	@Column(name = "status_code", nullable = false, unique = true, length = 8)
	private String statusCode;
	
	@Column(name = "status_name", nullable = false, length = 30)
	private String statusName;
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
}
