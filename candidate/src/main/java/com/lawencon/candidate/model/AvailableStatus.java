package com.lawencon.candidate.model;

import com.lawencon.base.BaseEntity;

public class AvailableStatus extends BaseEntity {
	private String statusCode;
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
