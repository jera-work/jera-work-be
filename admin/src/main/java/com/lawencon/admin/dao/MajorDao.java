package com.lawencon.admin.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.admin.model.Major;

@Repository
@Profile(value = { "native-query" })
public class MajorDao extends AbstractJpaDao {
	
	public Major getById(final Object id) {
		return super.getById(Major.class, id);
	}

	public Major getByIdRef(final Object id) {
		return super.getByIdRef(Major.class, id);
	}

	public Major getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(Major.class, id);
	}
	
	public List<Major> getAll() {
		return super.getAll(Major.class);
	}

	public Major save(Major data) {
		return super.save(data);
	}

	public Major saveAndFlush(Major entity) {
		return super.saveAndFlush(entity);
	}

	public Major saveNoLogin(Major entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(Major.class, entityId);
	}

}