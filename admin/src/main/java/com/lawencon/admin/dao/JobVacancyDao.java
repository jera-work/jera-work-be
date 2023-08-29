package com.lawencon.admin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.AvailableStatus;
import com.lawencon.admin.model.City;
import com.lawencon.admin.model.Company;
import com.lawencon.admin.model.Degree;
import com.lawencon.admin.model.ExperienceLevel;
import com.lawencon.admin.model.File;
import com.lawencon.admin.model.JobType;
import com.lawencon.admin.model.JobVacancy;
import com.lawencon.admin.model.Profile;
import com.lawencon.admin.model.User;
import com.lawencon.admin.model.VacancyDescription;
import com.lawencon.admin.util.DateUtil;
import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

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
	
	public JobVacancy getByCode(final String code) {
		final String sql = "SELECT "
				+ "tja.id, tja.company_id "
				+ "FROM "
				+ "t_job_vacancy tja "
				+ "INNER JOIN "
				+ "t_company tc ON tja.company_id = tc.id "
				+ "WHERE "
				+ "tja.vacancy_code LIKE :code";
		
		try {
			final Object jobObj = ConnHandler.getManager().createNativeQuery(sql)
					.setParameter("code", code)
					.getSingleResult();
			
			final Object[] jobObjArr = (Object[]) jobObj;

			JobVacancy job = null;
			if(jobObjArr.length > 0) {
				job = new JobVacancy();
				job.setId(jobObjArr[0].toString());
				
				final Company company = new Company();
				company.setId(jobObjArr[1].toString());
				
				job.setCompany(company);
			}
			return job;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<JobVacancy> getAllWithLimit(int startIndex, int endIndex, String vacancyTitle, String degreeId, String cityId, String jobTypeId){
		String sql = "SELECT "
				+ "	tjv.id, tjv.vacancy_code, tjv.vacancy_title, tc.company_name, tc.photo_id, "
				+ " tvd.salary, td.degree_name, tjt.type_name, tc2.city_name, tjv.created_at "
				+ "FROM "
				+ "	t_job_vacancy tjv "
				+ "INNER JOIN "
				+ "	t_company tc ON tjv.company_id = tc.id "
				+ "INNER JOIN "
				+ "	t_file tf ON tc.photo_id = tf.id "
				+ "INNER JOIN "
				+ "	t_vacancy_description tvd ON tjv.vacancy_description_id = tvd.id "
				+ "INNER JOIN "
				+ "	t_city tc2 ON tvd.city_id = tc2.id "
				+ "INNER JOIN "
				+ "	t_degree td ON tvd.degree_id = td.id "
				+ "INNER JOIN "
				+ "	t_job_type tjt ON tvd.job_type_id = tjt.id "
				+ "WHERE "
				+ " 1 = 1 ";
		
		if(vacancyTitle != null && !vacancyTitle.equalsIgnoreCase("")) {
			sql += "AND tjv.vacancy_title ILIKE '%' || :vacancyTitle || '%' ";
		}
		
		if(degreeId != null && !degreeId.equalsIgnoreCase("")) {
			sql += " AND tvd.degree_id ILIKE :degreeId || '%' ";
		}
		
		if(cityId != null && !cityId.equalsIgnoreCase("")) {
			sql += " AND tvd.city_id ILIKE :cityId || '%' ";
		}
		
		if(jobTypeId != null && !jobTypeId.equalsIgnoreCase("")) {
			sql += " AND tvd.job_type_id ILIKE :jobTypeId || '%' ";
		}
		
		final Query query = ConnHandler.getManager().createNativeQuery(sql);
		
		if(vacancyTitle != null && !vacancyTitle.equalsIgnoreCase("")) {
			query.setParameter("vacancyTitle", vacancyTitle);
		}
		
		if(degreeId != null && !degreeId.equalsIgnoreCase("")) {
			query.setParameter("degreeId", degreeId);
		}
		
		if(cityId != null && !cityId.equalsIgnoreCase("")) {
			query.setParameter("cityId", cityId);
		}
		
		if(jobTypeId != null && !jobTypeId.equalsIgnoreCase("")) {
			query.setParameter("jobTypeId", jobTypeId);
		}
		
		final List<?> jobObjs = query.getResultList();
		
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
				
				final File photo = new File();
				photo.setId(objArr[4].toString());
				company.setPhoto(photo);
				
				jobVacancy.setCompany(company);
				
				final VacancyDescription vacancyDescription = new VacancyDescription();
				vacancyDescription.setSalary(objArr[5].toString());
				
				final Degree degree = new Degree();
				degree.setDegreeName(objArr[6].toString());
				vacancyDescription.setDegree(degree);
				
				final JobType jobType = new JobType();
				jobType.setTypeName(objArr[7].toString());
				vacancyDescription.setJobType(jobType);
				
				final City city = new City();
				city.setCityName(objArr[8].toString());
				vacancyDescription.setCity(city);
				
				jobVacancy.setVacancyDescription(vacancyDescription);
				jobVacancy.setCreatedAt(DateUtil.dateTimeParseCustom(objArr[9].toString()));
				
				jobVacancies.add(jobVacancy);
			}
		}
		
		return jobVacancies;
	}

	public List<JobVacancy> getLatestJob(int startIndex, int endIndex){
		final String sql = "SELECT "
				+ "	tjv.id, tjv.vacancy_code, tjv.vacancy_title, "
				+ "tc.company_name, tc.photo_id, tvd.salary, "
				+ "td.degree_name, tjt.type_name , tc2.city_name, tjv.created_at "
				+ "FROM "
				+ "	t_job_vacancy tjv "
				+ "INNER JOIN "
				+ "	t_company tc ON tjv.company_id = tc.id "
				+ "INNER JOIN "
				+ "	t_file tf ON tc.photo_id = tf.id "
				+ "INNER JOIN "
				+ "	t_vacancy_description tvd ON tjv.vacancy_description_id = tvd.id "
				+ "INNER JOIN "
				+ "	t_city tc2 ON tvd.city_id = tc2.id "
				+ "INNER JOIN "
				+ "	t_degree td ON tvd.degree_id = td.id "
				+ "INNER JOIN "
				+ "	t_job_type tjt ON tvd.job_type_id = tjt.id "
				+ "ORDER BY "
				+ "	tjv.created_at DESC ";
		
		final List<?> jobObjs = ConnHandler.getManager()
				.createNativeQuery(sql)
				.setFirstResult(startIndex)
				.setMaxResults(endIndex)
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
				
				final File photo = new File();
				photo.setId(objArr[4].toString());
				company.setPhoto(photo);
				
				jobVacancy.setCompany(company);
				
				final VacancyDescription vacancyDescription = new VacancyDescription();
				vacancyDescription.setSalary(objArr[5].toString());
				
				final Degree degree = new Degree();
				degree.setDegreeName(objArr[6].toString());
				vacancyDescription.setDegree(degree);
				
				final JobType jobType = new JobType();
				jobType.setTypeName(objArr[7].toString());
				vacancyDescription.setJobType(jobType);
				
				final City city = new City();
				city.setCityName(objArr[8].toString());
				vacancyDescription.setCity(city);
				
				jobVacancy.setVacancyDescription(vacancyDescription);
				jobVacancy.setCreatedAt(DateUtil.dateTimeParseCustom(objArr[9].toString()));

				
				jobVacancies.add(jobVacancy);
			}
		}
		
		return jobVacancies;
	}
	
	public List<JobVacancy> getAll(int startIndex, int endIndex){
		final String sql = "SELECT "
				+ "	tjv.id, tjv.vacancy_code, tjv.vacancy_title, "
				+ "tc.company_name, tc.photo_id, tvd.salary, "
				+ "td.degree_name , tjt.type_name , tc2.city_name, tjv.created_at "
				+ "FROM "
				+ "	t_job_vacancy tjv "
				+ "INNER JOIN "
				+ "	t_company tc ON tjv.company_id = tc.id "
				+ "INNER JOIN "
				+ "	t_file tf ON tc.photo_id = tf.id "
				+ "INNER JOIN "
				+ "	t_vacancy_description tvd ON tjv.vacancy_description_id = tvd.id "
				+ "INNER JOIN "
				+ "	t_city tc2 ON tvd.city_id = tc2.id "
				+ "INNER JOIN "
				+ "	t_degree td ON tvd.degree_id = td.id "
				+ "INNER JOIN "
				+ "	t_job_type tjt ON tvd.job_type_id = tjt.id ";
		
		final List<?> jobObjs = ConnHandler.getManager()
				.createNativeQuery(sql)
				.setFirstResult(startIndex)
				.setMaxResults(endIndex)
				.getResultList();
		
		final List<JobVacancy> jobVacancies = new ArrayList<>();
		
		if(jobObjs.size() > 0) {
			for(Object jobObj : jobObjs) {
				final Object[] objArr = (Object[]) jobObj;
				final JobVacancy jobVacancy = new JobVacancy();
				
				jobVacancy.setId(objArr[0].toString());
				jobVacancy.setVacancyCode(objArr[1].toString());
				jobVacancy.setVacancyTitle(objArr[2].toString());
				
				final Company company = new Company();
				company.setCompanyName(objArr[3].toString());
				
				final File photo = new File();
				photo.setId(objArr[4].toString());
				company.setPhoto(photo);
				
				jobVacancy.setCompany(company);
				
				final VacancyDescription vacancyDescription = new VacancyDescription();
				vacancyDescription.setSalary(objArr[5].toString());
				
				final Degree degree = new Degree();
				degree.setDegreeName(objArr[6].toString());
				vacancyDescription.setDegree(degree);
				
				final JobType jobType = new JobType();
				jobType.setTypeName(objArr[7].toString());
				vacancyDescription.setJobType(jobType);
				
				final City city = new City();
				city.setCityName(objArr[8].toString());
				vacancyDescription.setCity(city);
				
				jobVacancy.setVacancyDescription(vacancyDescription);
				jobVacancy.setCreatedAt(DateUtil.dateTimeParseCustom(objArr[9].toString()));
				
				jobVacancies.add(jobVacancy);
			}
		}
		
		return jobVacancies;
	}
	
	public List<JobVacancy> getJobByCompany(int startIndex, int endIndex, String companyId){
		final String sql = "SELECT "
				+ "tjv.id, tjv.vacancy_code, tjv.vacancy_title, tp.profile_name as hrName, tp2.profile_name as userName, "
				+ "tjv.start_date, tjv.end_date, tel.level_name, tas.status_name, "
				+ "tc.company_name, tjv.vacancy_description_id "
				+ "FROM "
				+ "t_job_vacancy tjv "
				+ "INNER JOIN "
				+ "t_user tu ON tjv.pic_hr_id = tu.id "
				+ "INNER JOIN "
				+ "t_profile tp ON tu.profile_id = tp.id "
				+ "INNER JOIN "
				+ "t_user tu2 ON tjv.pic_user_id = tu2.id "
				+ "INNER JOIN "
				+ "t_profile tp2 ON tu2.profile_id = tp2.id "
				+ "INNER JOIN "
				+ "t_experience_level tel ON tjv.exp_level_id = tel.id "
				+ "INNER JOIN "
				+ "t_available_status tas ON tjv.available_status_id = tas.id "
				+ "INNER JOIN "
				+ "t_company tc ON tjv.company_id = tc.id "
				+ "INNER JOIN "
				+ "t_vacancy_description tvd ON tjv.vacancy_description_id = tvd.id "
				+ "WHERE "
				+ "tjv.company_id = :companyId";
		
		final List<?> jobObjs = ConnHandler.getManager()
				.createNativeQuery(sql)
				.setParameter("companyId", companyId)
				.setFirstResult(startIndex)
				.setMaxResults(endIndex)
				.getResultList();
		
		final List<JobVacancy> jobVacancies = new ArrayList<>();
		
		if(jobObjs.size() > 0) {
			for(Object jobObj:jobObjs) {
				final Object[] objArr = (Object[]) jobObj;
				final JobVacancy jobVacancy = new JobVacancy();
				
				jobVacancy.setId(objArr[0].toString());
				jobVacancy.setVacancyCode(objArr[1].toString());
				jobVacancy.setVacancyTitle(objArr[2].toString());
				
				final User picHr = new User();
				final Profile hrProfile = new Profile();
				hrProfile.setProfileName(objArr[3].toString());
				picHr.setProfile(hrProfile);
				jobVacancy.setPicHr(picHr);
				
				final User picUser = new User();
				final Profile userProfile = new Profile();
				userProfile.setProfileName(objArr[4].toString());
				picUser.setProfile(hrProfile);
				jobVacancy.setPicUser(picUser);
				
				jobVacancy.setStartDate(DateUtil.dateParseCustom((objArr[5].toString())));
				jobVacancy.setEndDate(DateUtil.dateParseCustom(objArr[6].toString()));
				
				final ExperienceLevel experienceLevel = new ExperienceLevel();
				experienceLevel.setLevelName(objArr[7].toString());
				jobVacancy.setExpLevel(experienceLevel);
				
				final AvailableStatus availableStatus = new AvailableStatus();
				availableStatus.setStatusname(objArr[8].toString());
				jobVacancy.setAvailableStatus(availableStatus);
				
				final Company company = new Company();
				company.setCompanyName(objArr[9].toString());
				jobVacancy.setCompany(company);
				
				final VacancyDescription vacancyDescription = new VacancyDescription();
				vacancyDescription.setId(objArr[10].toString());
				jobVacancy.setVacancyDescription(vacancyDescription);
				
				jobVacancies.add(jobVacancy);
			}
		}
		
		return jobVacancies;
	}
}