package com.lawencon.candidate.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.model.JobVacancy;
import com.lawencon.candidate.model.VacancyDescription;

@Repository
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
				+ "tjv.id, tjv.vacancy_description_id "
				+ "FROM "
				+ "t_job_vacancy tjv "
				+ "WHERE tjv.vacancy_code = :vacancyCode";
		
		final Object jobObj = ConnHandler.getManager().createNativeQuery(sql)
				.setParameter("vacancyCode", vacancyCode)
				.getSingleResult();
		
		final Object[] jobArr = (Object[]) jobObj;
		
		final VacancyDescription desc = new VacancyDescription();
		desc.setId(jobArr[1].toString());
		
		final JobVacancy jobVacancy = new JobVacancy();
		jobVacancy.setId(jobArr[0].toString());
		jobVacancy.setVacancyDescription(desc);
		
		return jobVacancy;
	}

}