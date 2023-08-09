package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_degree")
public class Degree extends BaseEntity{
	
	@Column(name = "degree_code", nullable = false, unique = true, length = 8)
	private String degreeCode;
	
	@Column(name = "degree_name", nullable = false, length = 8)
	private String degreeName;
	
	public String getDegreeCode() {
		return degreeCode;
	}
	public void setDegreeCode(String degreeCode) {
		this.degreeCode = degreeCode;
	}
	public String getDegreeName() {
		return degreeName;
	}
	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}
}
