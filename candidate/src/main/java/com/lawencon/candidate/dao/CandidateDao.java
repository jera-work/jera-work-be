package com.lawencon.candidate.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.candidate.model.Candidate;

@Repository
@Profile(value = { "native-query" })
public class CandidateDao extends AbstractJpaDao {

	public Candidate getById(final Object id) {
		return super.getById(Candidate.class, id);
	}

	public Candidate getByIdRef(final Object id) {
		return super.getByIdRef(Candidate.class, id);
	}

	public Candidate getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(Candidate.class, id);
	}
	
	public List<Candidate> getAll() {
		return super.getAll(Candidate.class);
	}

	public Candidate save(Candidate data) {
		return super.save(data);
	}

	public Candidate saveAndFlush(Candidate entity) {
		return super.saveAndFlush(entity);
	}

	public Candidate saveNoLogin(Candidate entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(Candidate.class, entityId);
	}
	
}