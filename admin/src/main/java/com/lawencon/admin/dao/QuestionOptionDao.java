package com.lawencon.admin.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.QuestionOption;
import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

@Repository
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
	
	@SuppressWarnings("unchecked")
	public List<QuestionOption> getByQuestionId(String questionId){
		final String sql = 
				"SELECT "
					+ "	* "	
				+ "FROM "
					+ "	t_question_option tqo "
				+ "INNER JOIN "
					+ "	t_question tq ON tq.id = tqo.question_id  "
				+ "WHERE "
					+ "	tq.id LIKE :questionId ";
		
		final List<QuestionOption> options = ConnHandler.getManager().createNativeQuery(sql, QuestionOption.class)
				.setParameter("questionId", questionId)
				.getResultList();
		
		return options;
	}
}