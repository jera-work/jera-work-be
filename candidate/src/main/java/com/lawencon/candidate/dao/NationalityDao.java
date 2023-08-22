package com.lawencon.candidate.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.candidate.model.Nationality;

@Repository
@Profile(value = { "native-query" })
public class NationalityDao extends AbstractJpaDao {
	
	public Nationality getById(final Object id) {
		return super.getById(Nationality.class, id);
	}

	public Nationality getByIdRef(final Object id) {
		return super.getByIdRef(Nationality.class, id);
	}

	public Nationality getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(Nationality.class, id);
	}
	
	public List<Nationality> getAll() {
		return super.getAll(Nationality.class);
	}

	public Nationality save(Nationality data) {
		return super.save(data);
	}

	public Nationality saveAndFlush(Nationality entity) {
		return super.saveAndFlush(entity);
	}

	public Nationality saveNoLogin(Nationality entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(Nationality.class, entityId);
	}

}