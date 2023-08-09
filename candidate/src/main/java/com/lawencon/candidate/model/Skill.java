package com.lawencon.candidate.model;

import com.lawencon.base.BaseEntity;

public class Skill extends BaseEntity{
	private String skillCode;
	private String skillName;
	
	public String getSkillCode() {
		return skillCode;
	}
	public void setSkillCode(String skillCode) {
		this.skillCode = skillCode;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
}
