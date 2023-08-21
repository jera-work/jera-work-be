package com.lawencon.candidate.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.candidate.model.CustomCandidateSkill;

@Repository
public class CustomSkillDao extends AbstractJpaDao {
	public CustomCandidateSkill getById(final Object id) {
		return super.getById(CustomCandidateSkill.class, id);
	}

	public CustomCandidateSkill getByIdRef(final Object id) {
		return super.getByIdRef(CustomCandidateSkill.class, id);
	}

	public CustomCandidateSkill getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(CustomCandidateSkill.class, id);
	}

	public List<CustomCandidateSkill> getAll() {
		return super.getAll(CustomCandidateSkill.class);
	}

	public CustomCandidateSkill save(CustomCandidateSkill data) {
		return super.save(data);
	}

	public CustomCandidateSkill saveAndFlush(CustomCandidateSkill entity) {
		return super.saveAndFlush(entity);
	}

	public CustomCandidateSkill saveNoLogin(CustomCandidateSkill entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(CustomCandidateSkill.class, entityId);
	}
}
