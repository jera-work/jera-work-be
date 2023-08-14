package com.lawencon.candidate.constant;

public enum AppliedStatusCode {
	ACTIVE("ACT", "Active"), CLOSED("CLS", "Closed");

	public final String statusCode;
	public final String statusName;

	AppliedStatusCode(String statusCode, String statusName) {
		this.statusCode = statusCode;
		this.statusName = statusName;
	}
}
