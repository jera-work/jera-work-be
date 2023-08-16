package com.lawencon.admin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.City;
import com.lawencon.admin.model.Company;
import com.lawencon.admin.model.Degree;
import com.lawencon.admin.model.JobType;
import com.lawencon.admin.model.JobVacancy;
import com.lawencon.admin.model.VacancyDescription;
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

	public List<JobVacancy> getAllWithLimit(int startIndex, int endIndex, String vacancyTitle, String degreeId, String cityId, String jobTypeId){
		final String sql = "SELECT "
				+ "	tjv.id, tjv.vacancy_code, tjv.vacancy_title, tc.company_name, "
				+ " tvd.salary, td.degree_name, tjt.type_name, tc2.city_name "
				+ "FROM "
				+ "	t_job_vacancy tjv "
				+ "INNER JOIN "
				+ "	t_company tc ON tjv.company_id = tc.id "
				+ "INNER JOIN "
				+ "	t_vacancy_description tvd ON tjv.vacancy_description_id = tvd.id "
				+ "INNER JOIN "
				+ "	t_city tc2 ON tvd.city_id = tc2.id "
				+ "INNER JOIN "
				+ "	t_degree td ON tvd.degree_id = td.id "
				+ "INNER JOIN "
				+ "	t_job_type tjt ON tvd.job_type_id = tjt.id "
				+ "WHERE "
				+ "	tjv.vacancy_title ILIKE '%' || :vacancyTitle || '%' "
				+ "AND "
				+ "	tvd.degree_id ILIKE :degreeId || '%' "
				+ "AND "
				+ "	tvd.city_id ILIKE :cityId || '%' "
				+ "AND "
				+ "	tvd.job_type_id ILIKE :jobTypeId || '%' ";
		
		if(vacancyTitle == null) {
			vacancyTitle = "";
		}
		
		if(degreeId == null) {
			degreeId = "";
		}
		
		if(cityId == null) {
			cityId = "";
		}
		
		if(jobTypeId == null) {
			jobTypeId = "";
		}
		
		final List<?> jobObjs = ConnHandler.getManager()
				.createNativeQuery(sql)
				.setParameter("vacancyTitle", vacancyTitle)
				.setParameter("degreeId", degreeId)
				.setParameter("cityId", cityId)
				.setParameter("jobTypeId", jobTypeId)
				.setFirstResult(startIndex)
				.setMaxResults(startIndex)
				.getResultList();
		
		final List<JobVacancy> jobVacancies = new ArrayList<>();
		
		if(jobObjs.size() > 0) {
			for(Object jobObj:jobObjs) {
				final Object[] objArr = (Object[]) jobObj;
				final JobVacancy jobVacancy = new JobVacancy();
				
				jobVacancy.setId(objArr[0].toString());
				jobVacancy.setVacancyCode(objArr[1].toString());
				jobVacancy.setVacancyTitle(objArr[2].toString());
				
				final Company company = new Company();
				company.setCompanyName(objArr[3].toString());
				jobVacancy.setCompany(company);
				
				final VacancyDescription vacancyDescription = new VacancyDescription();
				vacancyDescription.setSalary(objArr[4].toString());
				
				final Degree degree = new Degree();
				degree.setDegreeName(objArr[5].toString());
				vacancyDescription.setDegree(degree);
				
				final JobType jobType = new JobType();
				jobType.setTypeName(objArr[6].toString());
				vacancyDescription.setJobType(jobType);
				
				final City city = new City();
				city.setCityName(objArr[7].toString());
				vacancyDescription.setCity(city);
				
				jobVacancy.setVacancyDescription(vacancyDescription);
				
				jobVacancies.add(jobVacancy);
			}
		}
		
		return jobVacancies;
	}

}