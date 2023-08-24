package com.lawencon.candidate.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.candidate.model.McuVacancy;

@Repository
@Profile(value = { "native-query" })
public class McuVacancyDao extends AbstractJpaDao {
	
	public McuVacancy getById(final Object id) {
		return super.getById(McuVacancy.class, id);
	}

	public McuVacancy getByIdRef(final Object id) {
		return super.getByIdRef(McuVacancy.class, id);
	}

	public McuVacancy getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(McuVacancy.class, id);
	}
	
	public List<McuVacancy> getAll() {
		return super.getAll(McuVacancy.class);
	}

	public McuVacancy save(McuVacancy data) {
		return super.save(data);
	}

	public McuVacancy saveAndFlush(McuVacancy entity) {
		return super.saveAndFlush(entity);
	}

	public McuVacancy saveNoLogin(McuVacancy entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(McuVacancy.class, entityId);
	}

}