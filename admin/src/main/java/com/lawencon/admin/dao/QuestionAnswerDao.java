package com.lawencon.admin.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
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

	public Float countScore(String jobId, String candidateId) {
		final String sql = "SELECT "
				+ "	((100)/( "
				+ "		SELECT "
				+ "			COUNT(DISTINCT tq.id) "
				+ "		FROM "
				+ "			t_question tq "
				+ "		INNER JOIN "
				+ "			t_job_vacancy tjv ON tq.job_vacancy_id = tjv.id "
				+ "		INNER JOIN "
				+ "			t_question_option tqo2 ON tqo2.question_id = tq.id "
				+ "		WHERE "
				+ "			tjv.id = :jobId "
				+ "	))*COUNT(tqa.question_option_id) "
				+ "FROM "
				+ "	t_question_answer tqa "
				+ "INNER JOIN "
				+ "	t_question tq ON tqa.question_id = tq.id "
				+ "INNER JOIN "
				+ "	t_question_option tqo ON tqa.question_option_id = tqo.id "
				+ "INNER JOIN "
				+ "	t_applied_vacancy tav ON tqa.applied_vacancy_id = tav.id "
				+ "INNER JOIN "
				+ "	t_candidate tc ON tav.candidate_id = tc.id "
				+ "WHERE "
				+ "	tc.id = :candidateId AND tqo.is_correct = true "
				+ "GROUP BY "
				+ "	tc.id ";
		
		final Float result = ((BigInteger) ConnHandler.getManager()
				.createNativeQuery(sql)
				.setParameter("jobId", jobId)
				.setParameter("candidateId", candidateId)
				.getSingleResult()).floatValue();
		
		return result;
	}
}