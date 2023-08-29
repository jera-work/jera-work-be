package com.lawencon.admin.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.InterviewVacancy;
import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

@Repository
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
	public InterviewVacancy getByAppliedVacancyId(String appliedVacancyId) {
		final String sql = "SELECT "
				+ "	tiv.id, tiv.start_date, tiv.end_date, tiv.notes, tiv.interview_location "
				+ "FROM "
				+ "	t_interview_vacancy tiv  "
				+ "WHERE "
				+ "	tiv.applied_vacancy_id = :appliedVacancyId";
		
		try {
			final Object intObj = ConnHandler.getManager()
					.createNativeQuery(sql)
					.setParameter("appliedVacancyId", appliedVacancyId)
					.getSingleResult();
			
			final Object[] intObjArr = (Object[]) intObj;
			
			final InterviewVacancy interviewVacancy = new InterviewVacancy();
			interviewVacancy.setId(intObjArr[0].toString());
			interviewVacancy.setStartDate(LocalDate.parse(intObjArr[1].toString()));
			interviewVacancy.setEndDate(LocalDate.parse(intObjArr[2].toString()));
			interviewVacancy.setNotes(intObjArr[3].toString());
			interviewVacancy.setInterviewLocation(intObjArr[4].toString());
			
			return interviewVacancy;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}