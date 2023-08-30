package com.lawencon.admin.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.AssessmentVacancy;
import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

@Repository
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
	
	public AssessmentVacancy getByAppliedVacancyId(String appliedVacancyId) {
		final String sql = "SELECT "
				+ "	tav.id, tav.is_question, tav.score, tav.notes, tav.start_date, tav.end_date, tav.assessment_location "
				+ "FROM "
				+ "	t_assessment_vacancy tav "
				+ "WHERE "
				+ "	tav.applied_vacancy_id = :appliedVacancyId";

		try {
			
		final Object assObj = ConnHandler.getManager()
				.createNativeQuery(sql)
				.setParameter("appliedVacancyId", appliedVacancyId)
				.getSingleResult();
		
		final Object[] assObjArr = (Object[]) assObj;
		
			final AssessmentVacancy assessmentVacancy = new AssessmentVacancy();
			assessmentVacancy.setId(assObjArr[0].toString());
			assessmentVacancy.setIsQuestion(Boolean.valueOf(assObjArr[1].toString()));
			assessmentVacancy.setScore(Float.valueOf(assObjArr[2].toString()));
			assessmentVacancy.setNotes(assObjArr[3].toString());
			assessmentVacancy.setStartDate(Timestamp.valueOf(assObjArr[4].toString()).toLocalDateTime());
			assessmentVacancy.setEndDate(Timestamp.valueOf(assObjArr[5].toString()).toLocalDateTime());
			assessmentVacancy.setAssessmentLocation(assObjArr[6].toString());
			
			return assessmentVacancy;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}