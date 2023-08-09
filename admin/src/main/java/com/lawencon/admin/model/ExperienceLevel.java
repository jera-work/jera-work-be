package com.lawencon.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_experience_level")
public class ExperienceLevel extends BaseEntity {
	
	@Column(name = "level_code", nullable = false, unique = true, length = 8)
	private String levelCode;
	
	@Column(name = "level_name", nullable = false, length = 30)
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
