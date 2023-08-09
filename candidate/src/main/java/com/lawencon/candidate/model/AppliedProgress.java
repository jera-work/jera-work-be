package com.lawencon.candidate.model;

import com.lawencon.base.BaseEntity;

public class AppliedProgress extends BaseEntity{
	private String progressCode;
	private String progressName;
	
	public String getProgressCode() {
		return progressCode;
	}
	public void setProgressCode(String progressCode) {
		this.progressCode = progressCode;
	}
	public String getProgressName() {
		return progressName;
	}
	public void setProgressName(String progressName) {
		this.progressName = progressName;
	}
}
