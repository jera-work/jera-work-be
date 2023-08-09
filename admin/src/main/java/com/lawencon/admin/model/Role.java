package com.lawencon.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_role")
public class Role extends BaseEntity{
	
	@Column(name = "role_code", nullable = false, unique = true, length = 8)
	private String roleCode;
	
	@Column(name = "role_name", nullable = false, length = 30)
	private String roleName;
	
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
