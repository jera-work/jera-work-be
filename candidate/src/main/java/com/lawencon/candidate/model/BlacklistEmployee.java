package com.lawencon.candidate.model;

import com.lawencon.base.BaseEntity;

public class BlacklistEmployee extends BaseEntity {
	private Candidate employee;
	private Company company;
	
	public Candidate getEmployee() {
		return employee;
	}
	public void setEmployee(Candidate employee) {
		this.employee = employee;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
}
