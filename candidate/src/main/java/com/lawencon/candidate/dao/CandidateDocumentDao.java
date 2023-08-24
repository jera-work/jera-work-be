package com.lawencon.candidate.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.model.CandidateDocument;

@Repository
public class CandidateDocumentDao extends AbstractJpaDao {
	
	public CandidateDocument getById(final Object id) {
		return super.getById(CandidateDocument.class, id);
	}

	public CandidateDocument getByIdRef(final Object id) {
		return super.getByIdRef(CandidateDocument.class, id);
	}

	public CandidateDocument getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(CandidateDocument.class, id);
	}
	
	public List<CandidateDocument> getAll() {
		return super.getAll(CandidateDocument.class);
	}

	public CandidateDocument save(CandidateDocument data) {
		return super.save(data);
	}

	public CandidateDocument saveAndFlush(CandidateDocument entity) {
		return super.saveAndFlush(entity);
	}

	public CandidateDocument saveNoLogin(CandidateDocument entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(CandidateDocument.class, entityId);
	}
	
	@SuppressWarnings("unchecked")
	public List<CandidateDocument> getDocuments(String candidateId) {
		final String sql = "SELECT * FROM t_candidate_document WHERE candidate_id LIKE :candidateId ; ";

		final List<CandidateDocument> results = ConnHandler.getManager()
				.createNativeQuery(sql, CandidateDocument.class).setParameter("candidateId", candidateId)
				.getResultList();
		return results;
	}

}
