package com.lawencon.candidate.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.candidate.model.Skill;

@Repository
@Profile(value = { "native-query" })
public class SkillDao extends AbstractJpaDao {
	
	public Skill getById(final Object id) {
		return super.getById(Skill.class, id);
	}

	public Skill getByIdRef(final Object id) {
		return super.getByIdRef(Skill.class, id);
	}

	public Skill getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(Skill.class, id);
	}
	
	public List<Skill> getAll() {
		return super.getAll(Skill.class);
	}

	public Skill save(Skill data) {
		return super.save(data);
	}

	public Skill saveAndFlush(Skill entity) {
		return super.saveAndFlush(entity);
	}

	public Skill saveNoLogin(Skill entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(Skill.class, entityId);
	}

}