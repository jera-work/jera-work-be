package com.lawencon.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_user")
public class User extends BaseEntity{

	@Column(name = "user_email", nullable = false, length = 40)
	private String userEmail;
	
	@Column(name = "user_password", nullable = false, length = 8)
	private String userPassword;
	
	@OneToOne
	@JoinColumn(name = "profile_id", nullable = false)
	private Profile profile;
	
	@OneToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}
