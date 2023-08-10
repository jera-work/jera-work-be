package com.lawencon.admin.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.admin.model.CandidateDocument;

@Repository
@Profile(value = { "native-query" })
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

}
