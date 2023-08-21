package com.lawencon.admin.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.Profile;
import com.lawencon.base.AbstractJpaDao;

@Repository
public class ProfileDao extends AbstractJpaDao {
	
	public Profile getById(final Object id) {
		return super.getById(Profile.class, id);
	}

	public Profile getByIdRef(final Object id) {
		return super.getByIdRef(Profile.class, id);
	}

	public Profile getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(Profile.class, id);
	}
	
	public List<Profile> getAll() {
		return super.getAll(Profile.class);
	}

	public Profile save(Profile data) {
		return super.save(data);
	}

	public Profile saveAndFlush(Profile entity) {
		return super.saveAndFlush(entity);
	}

	public Profile saveNoLogin(Profile entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(Profile.class, entityId);
	}

}