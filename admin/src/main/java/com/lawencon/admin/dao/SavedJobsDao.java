package com.lawencon.admin.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.admin.model.SavedJobs;

@Repository
@Profile(value = { "native-query" })
public class SavedJobsDao extends AbstractJpaDao {
	
	public SavedJobs getById(final Object id) {
		return super.getById(SavedJobs.class, id);
	}

	public SavedJobs getByIdRef(final Object id) {
		return super.getByIdRef(SavedJobs.class, id);
	}

	public SavedJobs getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(SavedJobs.class, id);
	}
	
	public List<SavedJobs> getAll() {
		return super.getAll(SavedJobs.class);
	}

	public SavedJobs save(SavedJobs data) {
		return super.save(data);
	}

	public SavedJobs saveAndFlush(SavedJobs entity) {
		return super.saveAndFlush(entity);
	}

	public SavedJobs saveNoLogin(SavedJobs entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(SavedJobs.class, entityId);
	}

}