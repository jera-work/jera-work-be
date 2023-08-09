package com.lawencon.candidate.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name="t_candidate_document")
public class CandidateDocument extends BaseEntity {
	
	@OneToOne
	@JoinColumn(name="candidate_id")
	private Candidate candidate;
	@OneToOne
	@JoinColumn(name="file_id")
	private File file;
	@OneToOne
	@JoinColumn(name="document_type_id")
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
