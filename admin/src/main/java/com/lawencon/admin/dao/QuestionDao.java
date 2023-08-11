package com.lawencon.admin.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.admin.model.Question;

@Repository
@Profile(value = { "native-query" })
public class QuestionDao extends AbstractJpaDao {
	
	public Question getById(final Object id) {
		return super.getById(Question.class, id);
	}

	public Question getByIdRef(final Object id) {
		return super.getByIdRef(Question.class, id);
	}

	public Question getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(Question.class, id);
	}
	
	public List<Question> getAll() {
		return super.getAll(Question.class);
	}

	public Question save(Question data) {
		return super.save(data);
	}

	public Question saveAndFlush(Question entity) {
		return super.saveAndFlush(entity);
	}

	public Question saveNoLogin(Question entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(Question.class, entityId);
	}

}