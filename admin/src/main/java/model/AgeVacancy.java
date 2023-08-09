package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_age_vacancy")
public class AgeVacancy extends BaseEntity {
	
	@Column(name = "age_code", nullable = false, unique = true, length = 8)
	private String ageCode;
	
	@Column(name = "age_name", nullable = false, length = 30)
	private String ageName;
	
	public String getAgeCode() {
		return ageCode;
	}
	public void setAgeCode(String ageCode) {
		this.ageCode = ageCode;
	}
	public String getAgeName() {
		return ageName;
	}
	public void setAgeName(String ageName) {
		this.ageName = ageName;
	}
}
