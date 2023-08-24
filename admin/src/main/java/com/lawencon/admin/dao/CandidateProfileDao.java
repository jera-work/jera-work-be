package com.lawencon.admin.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.CandidateProfile;
import com.lawencon.base.AbstractJpaDao;

@Repository
public class CandidateProfileDao extends AbstractJpaDao {
	
	public CandidateProfile getById(final Object id) {
		return super.getById(CandidateProfile.class, id);
	}

	public CandidateProfile getByIdRef(final Object id) {
		return super.getByIdRef(CandidateProfile.class, id);
	}

	public CandidateProfile getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(CandidateProfile.class, id);
	}
	
	public List<CandidateProfile> getAll() {
		return super.getAll(CandidateProfile.class);
	}

	public CandidateProfile save(CandidateProfile data) {
		return super.save(data);
	}

	public CandidateProfile saveAndFlush(CandidateProfile entity) {
		return super.saveAndFlush(entity);
	}

	public CandidateProfile saveNoLogin(CandidateProfile entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(CandidateProfile.class, entityId);
	}

}
