package com.lawencon.admin.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.admin.model.CandidateExperience;

@Repository
@Profile(value = { "native-query" })
public class CandidateExperienceDao extends AbstractJpaDao {
	
	public CandidateExperience getById(final Object id) {
		return super.getById(CandidateExperience.class, id);
	}

	public CandidateExperience getByIdRef(final Object id) {
		return super.getByIdRef(CandidateExperience.class, id);
	}

	public CandidateExperience getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(CandidateExperience.class, id);
	}
	
	public List<CandidateExperience> getAll() {
		return super.getAll(CandidateExperience.class);
	}

	public CandidateExperience save(CandidateExperience data) {
		return super.save(data);
	}

	public CandidateExperience saveAndFlush(CandidateExperience entity) {
		return super.saveAndFlush(entity);
	}

	public CandidateExperience saveNoLogin(CandidateExperience entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(CandidateExperience.class, entityId);
	}

}
