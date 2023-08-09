package com.lawencon.candidate.model;

import com.lawencon.base.BaseEntity;

public class CandidateDocument extends BaseEntity{
	private Candidate candidate;
	private File file;
	private DocumentType documentType;
	

	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public DocumentType getDocumentType() {
		return documentType;
	}
	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}
}
