package com.lawencon.admin.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.JobVacancy;
import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

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
	
	public JobVacancy getByCode(final String code) {
		final String sql = "SELECT id FROM t_job_vacancy WHERE vacancy_code LIKE :code";
		
		try {
			final Object jobObj = ConnHandler.getManager().createNativeQuery(sql)
					.setParameter("code", code)
					.getSingleResult();
			
			final JobVacancy job = new JobVacancy();
			job.setId(jobObj.toString());
			return job;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}