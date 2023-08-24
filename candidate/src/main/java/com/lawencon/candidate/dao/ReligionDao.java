package com.lawencon.candidate.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.candidate.model.Religion;

@Repository
@Profile(value = { "native-query" })
public class ReligionDao extends AbstractJpaDao {
	
	public Religion getById(final Object id) {
		return super.getById(Religion.class, id);
	}

	public Religion getByIdRef(final Object id) {
		return super.getByIdRef(Religion.class, id);
	}

	public Religion getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(Religion.class, id);
	}
	
	public List<Religion> getAll() {
		return super.getAll(Religion.class);
	}

	public Religion save(Religion data) {
		return super.save(data);
	}

	public Religion saveAndFlush(Religion entity) {
		return super.saveAndFlush(entity);
	}

	public Religion saveNoLogin(Religion entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(Religion.class, entityId);
	}

}