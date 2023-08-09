package com.lawencon.candidate.model;

import com.lawencon.base.BaseEntity;

public class DocumentType extends BaseEntity{
	private String typeCode;
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
