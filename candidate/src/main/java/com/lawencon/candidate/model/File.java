package com.lawencon.candidate.model;

import com.lawencon.base.BaseEntity;

public class File extends BaseEntity{
	private String fileContent;
	private String fileExt;
	
	public String getFileContent() {
		return fileContent;
	}
	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}
	public String getFileExt() {
		return fileExt;
	}
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
}
