package com.lawencon.admin.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.AgeVacancy;
import com.lawencon.base.AbstractJpaDao;

@Repository
public class AgeVacancyDao extends AbstractJpaDao {
	
	public AgeVacancy getById(final Object id) {
		return super.getById(AgeVacancy.class, id);
	}

	public AgeVacancy getByIdRef(final Object id) {
		return super.getByIdRef(AgeVacancy.class, id);
	}

	public AgeVacancy getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(AgeVacancy.class, id);
	}
	
	public List<AgeVacancy> getAll() {
		return super.getAll(AgeVacancy.class);
	}

	public AgeVacancy save(AgeVacancy data) {
		return super.save(data);
	}

	public AgeVacancy saveAndFlush(AgeVacancy entity) {
		return super.saveAndFlush(entity);
	}

	public AgeVacancy saveNoLogin(AgeVacancy entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(AgeVacancy.class, entityId);
	}

}