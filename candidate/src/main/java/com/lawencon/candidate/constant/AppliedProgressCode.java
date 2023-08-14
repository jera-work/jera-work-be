package com.lawencon.candidate.constant;

public enum AppliedProgressCode {
	APPLICATION("APP", "Application"), ASSESMENT("ASS", "Assesment"), INTERVIEW_USER("INT", "Interview User"),
	MEDICAL_CHECKUP("MCU", "Medical Checkup"), OFFERING_LETTER("OFL", "Offering Letter"), HIRED("HIR", "HIRED");

	public final String progressCode;
	public final String progressName;

	AppliedProgressCode(String progressCode, String progressName) {
		this.progressCode = progressCode;
		this.progressName = progressName;
	}
}
