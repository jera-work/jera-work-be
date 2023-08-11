package com.lawencon.admin.constant;

public enum RoleCode {
	SYSTEM("SYS", "System"), SUPER_ADMIN("SA", "Super Admin"), ADMIN("ADM", "Admin"),
	HR("HR", "HR"), USER("USR", "User"), CANDIDATE("CND", "Candidate");

	public final String roleCode;
	public final String roleName;

	RoleCode(String roleCode, String roleName) {
		this.roleCode = roleCode;
		this.roleName = roleName;
	}
}
