package com.lawencon.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_available_status")
public class AvailableStatus extends BaseEntity {
	
	@Column(name = "status_code", nullable = false, unique = true, length = 8)
	private String statusCode;
	
	@Column(name = "status_name", nullable = false, length = 30)
	private String statusname;
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusname() {
		return statusname;
	}
	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}
}
