package com.lawencon.admin.dao;

import java.util.List;
import java.util.function.Supplier;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.admin.model.BlacklistEmployee;

public class BlacklistEmployeeDao extends AbstractJpaDao {
	
	public BlacklistEmployee getById(final Object id) {
		return super.getById(BlacklistEmployee.class, id);
	}

	public BlacklistEmployee getByIdRef(final Object id) {
		return super.getByIdRef(BlacklistEmployee.class, id);
	}

	public BlacklistEmployee getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(BlacklistEmployee.class, id);
	}
	
	public List<BlacklistEmployee> getAll() {
		return super.getAll(BlacklistEmployee.class);
	}

	public BlacklistEmployee save(BlacklistEmployee data) {
		return super.save(data);
	}

	public BlacklistEmployee saveAndFlush(BlacklistEmployee entity) {
		return super.saveAndFlush(entity);
	}

	public BlacklistEmployee saveNoLogin(BlacklistEmployee entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(BlacklistEmployee.class, entityId);
	}

}