package com.lawencon.candidate.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.candidate.model.CandidateEducation;

@Repository
@Profile(value = { "native-query" })
public class CandidateEducationDao extends AbstractJpaDao {
	
	public CandidateEducation getById(final Object id) {
		return super.getById(CandidateEducation.class, id);
	}

	public CandidateEducation getByIdRef(final Object id) {
		return super.getByIdRef(CandidateEducation.class, id);
	}

	public CandidateEducation getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(CandidateEducation.class, id);
	}
	
	public List<CandidateEducation> getAll() {
		return super.getAll(CandidateEducation.class);
	}

	public CandidateEducation save(CandidateEducation data) {
		return super.save(data);
	}

	public CandidateEducation saveAndFlush(CandidateEducation entity) {
		return super.saveAndFlush(entity);
	}

	public CandidateEducation saveNoLogin(CandidateEducation entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(CandidateEducation.class, entityId);
	}

}
