package com.lawencon.admin.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.HiredEmployee;
import com.lawencon.base.AbstractJpaDao;

@Repository
public class HiredEmployeeDao extends AbstractJpaDao {
	
	public HiredEmployee getById(final Object id) {
		return super.getById(HiredEmployee.class, id);
	}

	public HiredEmployee getByIdRef(final Object id) {
		return super.getByIdRef(HiredEmployee.class, id);
	}

	public HiredEmployee getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(HiredEmployee.class, id);
	}
	
	public List<HiredEmployee> getAll() {
		return super.getAll(HiredEmployee.class);
	}

	public HiredEmployee save(HiredEmployee data) {
		return super.save(data);
	}

	public HiredEmployee saveAndFlush(HiredEmployee entity) {
		return super.saveAndFlush(entity);
	}

	public HiredEmployee saveNoLogin(HiredEmployee entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(HiredEmployee.class, entityId);
	}

}