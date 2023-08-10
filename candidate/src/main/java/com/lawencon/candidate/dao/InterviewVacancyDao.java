package com.lawencon.candidate.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.candidate.model.InterviewVacancy;

@Repository
@Profile(value = { "native-query" })
public class InterviewVacancyDao extends AbstractJpaDao {
	
	public InterviewVacancy getById(final Object id) {
		return super.getById(InterviewVacancy.class, id);
	}

	public InterviewVacancy getByIdRef(final Object id) {
		return super.getByIdRef(InterviewVacancy.class, id);
	}

	public InterviewVacancy getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(InterviewVacancy.class, id);
	}
	
	public List<InterviewVacancy> getAll() {
		return super.getAll(InterviewVacancy.class);
	}

	public InterviewVacancy save(InterviewVacancy data) {
		return super.save(data);
	}

	public InterviewVacancy saveAndFlush(InterviewVacancy entity) {
		return super.saveAndFlush(entity);
	}

	public InterviewVacancy saveNoLogin(InterviewVacancy entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(InterviewVacancy.class, entityId);
	}

}