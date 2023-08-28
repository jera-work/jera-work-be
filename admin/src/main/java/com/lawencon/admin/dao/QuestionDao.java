package com.lawencon.admin.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.Question;
import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

@Repository
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
	
	@SuppressWarnings("unchecked")
	public List<Question> getByJobId(String jobId){
		final String sql = 
				"SELECT "
					+ "	* "
				+ "FROM "
					+ "	t_question tq "
				+ "INNER JOIN "
					+ "	t_job_vacancy tjv ON tjv.id = tq.job_vacancy_id "
				+ "WHERE "
					+ "	tjv.id LIKE :jobId ";
		
		final List<Question> questions = ConnHandler.getManager().createNativeQuery(sql, Question.class)
				.setParameter("jobId", jobId)
				.getResultList();
		
		return questions;
	}
}