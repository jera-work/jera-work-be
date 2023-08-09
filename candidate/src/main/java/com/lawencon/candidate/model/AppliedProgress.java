package com.lawencon.candidate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_applied_progress")
public class AppliedProgress extends BaseEntity{
	
	@Column(name = "progres_code", nullable = false, unique = true, length = 8)
	private String progressCode;
	
	@Column(name = "progress_name", nullable = false, length = 30)
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
