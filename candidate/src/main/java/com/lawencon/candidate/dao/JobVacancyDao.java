package com.lawencon.candidate.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.model.JobVacancy;

@Repository
@Profile(value = { "native-query" })
public class JobVacancyDao extends AbstractJpaDao {
	
	public JobVacancy getById(final Object id) {
		return super.getById(JobVacancy.class, id);
	}

	public JobVacancy getByIdRef(final Object id) {
		return super.getByIdRef(JobVacancy.class, id);
	}

	public JobVacancy getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(JobVacancy.class, id);
	}
	
	public List<JobVacancy> getAll() {
		return super.getAll(JobVacancy.class);
	}

	public JobVacancy save(JobVacancy data) {
		return super.save(data);
	}

	public JobVacancy saveAndFlush(JobVacancy entity) {
		return super.saveAndFlush(entity);
	}

	public JobVacancy saveNoLogin(JobVacancy entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(JobVacancy.class, entityId);
	}

	public JobVacancy getByCode(String vacancyCode)  {
		final String sql = "SELECT "
				+ "jv.id "
				+ "FROM "
				+ "JobVacancy jv "
				+ "WHERE jv.vacancyCode = :vacancyCode";
		final Object jobObj = ConnHandler.getManager().createQuery(sql)
				.setParameter("vacancyCode", vacancyCode)
				.getSingleResult();
		
		final JobVacancy jobVacancy = new JobVacancy();
		jobVacancy.setVacancyCode(jobObj.toString());
		
		return jobVacancy;
	}
	
//	public List<JobVacancy> getAllWithLimit(int limit){
//		final String sql = "SELECT "
//				+ "	tjv.id, tjv.vancacy_code, tjv.vacancy_title, tvd.salary, tvd.degree_id, tvd.job_type_id, tvd.city_id "
//				+ "FROM "
//				+ "	t_job_vacancy tjv "
//				+ "INNER JOIN "
//				+ "	t_vacancy_description tvd ON tjv.vacancy_description_id = tvd.id ";
//		
//		final Object jobObj = ConnHandler.getManager().createQuery(sql).setMaxResults(limit);
//		
//		
//	}
}