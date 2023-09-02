package com.lawencon.admin.dto.hiredemployee;

import java.time.LocalDateTime;

public class HiredAppliedRangeDate {
	private LocalDateTime appliedAt;
	private LocalDateTime hiredAt;
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
	
	
}
