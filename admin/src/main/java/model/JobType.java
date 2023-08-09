package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_job_type")
public class JobType extends BaseEntity {
	
	@Column(name = "type_code", nullable = false, unique = true, length = 8)
	private String typeCode;
	
	@Column(name = "type_name", nullable = false, length = 30)
	private String typeName;
	
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
