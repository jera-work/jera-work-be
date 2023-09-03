package com.lawencon.admin.dto.hiredemployee;

import java.time.LocalDateTime;

public class HiredAppliedRangeDate {
	private LocalDateTime appliedAt;
	private LocalDateTime hiredAt;
	private String profileName;
	public LocalDateTime getAppliedAt() {
		return appliedAt;
	}
	public void setAppliedAt(LocalDateTime appliedAt) {
		this.appliedAt = appliedAt;
	}
	public LocalDateTime getHiredAt() {
		return hiredAt;
	}
	public void setHiredAt(LocalDateTime hiredAt) {
		this.hiredAt = hiredAt;
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	
	
}
