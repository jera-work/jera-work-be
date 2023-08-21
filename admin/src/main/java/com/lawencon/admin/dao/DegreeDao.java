package com.lawencon.admin.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.Degree;
import com.lawencon.base.AbstractJpaDao;

@Repository
public class DegreeDao extends AbstractJpaDao {
	
	public Degree getById(final Object id) {
		return super.getById(Degree.class, id);
	}

	public Degree getByIdRef(final Object id) {
		return super.getByIdRef(Degree.class, id);
	}

	public Degree getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(Degree.class, id);
	}
	
	public List<Degree> getAll() {
		return super.getAll(Degree.class);
	}

	public Degree save(Degree data) {
		return super.save(data);
	}

	public Degree saveAndFlush(Degree entity) {
		return super.saveAndFlush(entity);
	}

	public Degree saveNoLogin(Degree entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(Degree.class, entityId);
	}

}