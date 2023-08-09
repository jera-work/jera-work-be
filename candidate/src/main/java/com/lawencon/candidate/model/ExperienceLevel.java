package com.lawencon.candidate.model;

import com.lawencon.base.BaseEntity;

public class ExperienceLevel extends BaseEntity {
	private String levelCode;
	private String levelName;
	
	public String getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
}
