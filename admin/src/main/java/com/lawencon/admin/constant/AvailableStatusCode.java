package com.lawencon.admin.constant;

public enum AvailableStatusCode {
	OPEN("OPN", "Open"), CLOSED("CLS", "Closed");

	public final String statusCode;
	public final String statusName;

	AvailableStatusCode(String statusCode, String statusName) {
		this.statusCode = statusCode;
		this.statusName = statusName;
	}
}
