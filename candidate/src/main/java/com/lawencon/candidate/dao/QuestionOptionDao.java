package com.lawencon.candidate.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.candidate.model.QuestionOption;

@Repository
@Profile(value = { "native-query" })
public class QuestionOptionDao extends AbstractJpaDao {
	
	public QuestionOption getById(final Object id) {
		return super.getById(QuestionOption.class, id);
	}

	public QuestionOption getByIdRef(final Object id) {
		return super.getByIdRef(QuestionOption.class, id);
	}

	public QuestionOption getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(QuestionOption.class, id);
	}
	
	public List<QuestionOption> getAll() {
		return super.getAll(QuestionOption.class);
	}

	public QuestionOption save(QuestionOption data) {
		return super.save(data);
	}

	public QuestionOption saveAndFlush(QuestionOption entity) {
		return super.saveAndFlush(entity);
	}

	public QuestionOption saveNoLogin(QuestionOption entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(QuestionOption.class, entityId);
	}

}