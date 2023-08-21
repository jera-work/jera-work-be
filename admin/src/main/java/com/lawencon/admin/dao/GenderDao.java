package com.lawencon.admin.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.Gender;
import com.lawencon.base.AbstractJpaDao;

@Repository
public class GenderDao extends AbstractJpaDao {
	
	public Gender getById(final Object id) {
		return super.getById(Gender.class, id);
	}

	public Gender getByIdRef(final Object id) {
		return super.getByIdRef(Gender.class, id);
	}

	public Gender getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(Gender.class, id);
	}
	
	public List<Gender> getAll() {
		return super.getAll(Gender.class);
	}

	public Gender save(Gender data) {
		return super.save(data);
	}

	public Gender saveAndFlush(Gender entity) {
		return super.saveAndFlush(entity);
	}

	public Gender saveNoLogin(Gender entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(Gender.class, entityId);
	}

}