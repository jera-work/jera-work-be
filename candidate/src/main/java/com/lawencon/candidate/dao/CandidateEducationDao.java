package com.lawencon.candidate.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.model.CandidateEducation;

@Repository
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
	
	@SuppressWarnings("unchecked")
	public List<CandidateEducation> getByCandidateId(String candidateId){
		final String sql = "SELECT * FROM t_candidate_education WHERE candidate_id LIKE :candidateId ; ";
		
		final List<CandidateEducation> results = ConnHandler.getManager().createNativeQuery(sql, CandidateEducation.class)
				.setParameter("candidateId", candidateId)
				.getResultList();
		return results;
	}

}
