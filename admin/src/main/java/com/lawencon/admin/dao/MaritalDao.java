package com.lawencon.admin.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.Marital;
import com.lawencon.base.AbstractJpaDao;

@Repository
public class MaritalDao extends AbstractJpaDao {
	
	public Marital getById(final Object id) {
		return super.getById(Marital.class, id);
	}

	public Marital getByIdRef(final Object id) {
		return super.getByIdRef(Marital.class, id);
	}

	public Marital getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(Marital.class, id);
	}
	
	public List<Marital> getAll() {
		return super.getAll(Marital.class);
	}

	public Marital save(Marital data) {
		return super.save(data);
	}

	public Marital saveAndFlush(Marital entity) {
		return super.saveAndFlush(entity);
	}

	public Marital saveNoLogin(Marital entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(Marital.class, entityId);
	}

}