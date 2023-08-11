package com.lawencon.admin.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.admin.model.ExperienceLevel;

@Repository
@Profile(value = { "native-query" })
public class ExperienceLevelDao extends AbstractJpaDao {
	
	public ExperienceLevel getById(final Object id) {
		return super.getById(ExperienceLevel.class, id);
	}

	public ExperienceLevel getByIdRef(final Object id) {
		return super.getByIdRef(ExperienceLevel.class, id);
	}

	public ExperienceLevel getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(ExperienceLevel.class, id);
	}
	
	public List<ExperienceLevel> getAll() {
		return super.getAll(ExperienceLevel.class);
	}

	public ExperienceLevel save(ExperienceLevel data) {
		return super.save(data);
	}

	public ExperienceLevel saveAndFlush(ExperienceLevel entity) {
		return super.saveAndFlush(entity);
	}

	public ExperienceLevel saveNoLogin(ExperienceLevel entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(ExperienceLevel.class, entityId);
	}

}