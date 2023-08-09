package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_nationality")
public class Nationality extends BaseEntity{
	
	@Column(name = "nationality_code", nullable = false, unique = true, length = 8)
	private String nationalityCode;
	
	@Column(name = "nationality_name", nullable = false, length = 30)
	private String nationalityName;
	
	public String getNationalityCode() {
		return nationalityCode;
	}
	public void setNationalityCode(String nationalityCode) {
		this.nationalityCode = nationalityCode;
	}
	public String getNationalityName() {
		return nationalityName;
	}
	public void setNationalityName(String nationalityName) {
		this.nationalityName = nationalityName;
	}
}
