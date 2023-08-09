package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_skill")
public class Skill extends BaseEntity{
	
	@Column(name = "skill_code", nullable = false, unique = true, length = 8)
	private String skillCode;
	
	@Column(name = "skill_name", nullable = false, length = 30)
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
