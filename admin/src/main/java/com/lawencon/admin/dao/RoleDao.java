package com.lawencon.admin.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.Role;
import com.lawencon.base.AbstractJpaDao;

@Repository
public class RoleDao extends AbstractJpaDao {
	
	public Role getById(final Object id) {
		return super.getById(Role.class, id);
	}

	public Role getByIdRef(final Object id) {
		return super.getByIdRef(Role.class, id);
	}

	public Role getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(Role.class, id);
	}
	
	public List<Role> getAll() {
		return super.getAll(Role.class);
	}

	public Role save(Role data) {
		return super.save(data);
	}

	public Role saveAndFlush(Role entity) {
		return super.saveAndFlush(entity);
	}

	public Role saveNoLogin(Role entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(Role.class, entityId);
	}

}
