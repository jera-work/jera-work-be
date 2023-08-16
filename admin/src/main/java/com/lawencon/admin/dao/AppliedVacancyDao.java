package com.lawencon.admin.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.AppliedVacancy;
import com.lawencon.base.AbstractJpaDao;

@Repository
@Profile(value = { "native-query" })
public class AppliedVacancyDao extends AbstractJpaDao {
	
	public AppliedVacancy getById(final Object id) {
		return super.getById(AppliedVacancy.class, id);
	}

	public AppliedVacancy getByIdRef(final Object id) {
		return super.getByIdRef(AppliedVacancy.class, id);
	}

	public AppliedVacancy getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(AppliedVacancy.class, id);
	}
	
	public List<AppliedVacancy> getAll() {
		return super.getAll(AppliedVacancy.class);
	}

	public AppliedVacancy save(AppliedVacancy data) {
		return super.save(data);
	}

	public AppliedVacancy saveAndFlush(AppliedVacancy entity) {
		return super.saveAndFlush(entity);
	}

	public AppliedVacancy saveNoLogin(AppliedVacancy entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(AppliedVacancy.class, entityId);
	}

}