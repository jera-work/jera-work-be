package com.lawencon.admin.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.admin.model.AssessmentVacancy;

@Repository
@Profile(value = { "native-query" })
public class AssessmentVacancyDao extends AbstractJpaDao {
	
	public AssessmentVacancy getById(final Object id) {
		return super.getById(AssessmentVacancy.class, id);
	}

	public AssessmentVacancy getByIdRef(final Object id) {
		return super.getByIdRef(AssessmentVacancy.class, id);
	}

	public AssessmentVacancy getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(AssessmentVacancy.class, id);
	}
	
	public List<AssessmentVacancy> getAll() {
		return super.getAll(AssessmentVacancy.class);
	}

	public AssessmentVacancy save(AssessmentVacancy data) {
		return super.save(data);
	}

	public AssessmentVacancy saveAndFlush(AssessmentVacancy entity) {
		return super.saveAndFlush(entity);
	}

	public AssessmentVacancy saveNoLogin(AssessmentVacancy entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(AssessmentVacancy.class, entityId);
	}

}