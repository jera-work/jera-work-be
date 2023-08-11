package com.lawencon.admin.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.admin.model.QuestionAnswer;

@Repository
@Profile(value = { "native-query" })
public class QuestionAnswerDao extends AbstractJpaDao {
	
	public QuestionAnswer getById(final Object id) {
		return super.getById(QuestionAnswer.class, id);
	}

	public QuestionAnswer getByIdRef(final Object id) {
		return super.getByIdRef(QuestionAnswer.class, id);
	}

	public QuestionAnswer getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(QuestionAnswer.class, id);
	}
	
	public List<QuestionAnswer> getAll() {
		return super.getAll(QuestionAnswer.class);
	}

	public QuestionAnswer save(QuestionAnswer data) {
		return super.save(data);
	}

	public QuestionAnswer saveAndFlush(QuestionAnswer entity) {
		return super.saveAndFlush(entity);
	}

	public QuestionAnswer saveNoLogin(QuestionAnswer entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(QuestionAnswer.class, entityId);
	}

}