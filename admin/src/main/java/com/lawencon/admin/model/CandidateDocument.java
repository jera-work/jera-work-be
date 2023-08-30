package com.lawencon.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_candidate_document")
public class CandidateDocument extends BaseEntity {

	@Column(name = "document_code", length = 5, nullable = false)
	private String documentCode;

	@OneToOne
	@JoinColumn(name = "candidate_id", nullable = false)
	private Candidate candidate;

	@OneToOne
	@JoinColumn(name = "file_id", nullable = false)
	private File file;

	@OneToOne
	@JoinColumn(name = "document_type_id", nullable = false)
	private DocumentType documentType;

	public String getDocumentCode() {
		return documentCode;
	}

	public void setDocumentCode(String documentCode) {
		this.documentCode = documentCode;
	}

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
