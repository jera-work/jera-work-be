package com.lawencon.candidate.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.model.CandidateSkill;

@Repository
public class CandidateSkillDao extends AbstractJpaDao {

	public CandidateSkill getById(final Object id) {
		return super.getById(CandidateSkill.class, id);
	}

	public CandidateSkill getByIdRef(final Object id) {
		return super.getByIdRef(CandidateSkill.class, id);
	}

	public CandidateSkill getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(CandidateSkill.class, id);
	}

	public List<CandidateSkill> getAll() {
		return super.getAll(CandidateSkill.class);
	}

	public CandidateSkill save(CandidateSkill data) {
		return super.save(data);
	}

	public CandidateSkill saveAndFlush(CandidateSkill entity) {
		return super.saveAndFlush(entity);
	}

	public CandidateSkill saveNoLogin(CandidateSkill entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(CandidateSkill.class, entityId);
	}
	
	@SuppressWarnings("unchecked")
	public List<CandidateSkill> getByCandidateId(String candidateId){
		final String sql = "SELECT * FROM t_candidate_skill WHERE candidate_id LIKE :candidateId ; ";
		
		final List<CandidateSkill> results = ConnHandler.getManager().createNativeQuery(sql, CandidateSkill.class)
				.setParameter("candidateId", candidateId)
				.getResultList();
		return results;
	}

}
