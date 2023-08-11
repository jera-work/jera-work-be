package com.lawencon.admin.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.admin.constant.RoleCode;
import com.lawencon.admin.dao.AgeVacancyDao;
import com.lawencon.admin.dao.AvailableStatusDao;
import com.lawencon.admin.dao.CityDao;
import com.lawencon.admin.dao.CompanyDao;
import com.lawencon.admin.dao.DegreeDao;
import com.lawencon.admin.dao.ExperienceLevelDao;
import com.lawencon.admin.dao.GenderDao;
import com.lawencon.admin.dao.JobTypeDao;
import com.lawencon.admin.dao.JobVacancyDao;
import com.lawencon.admin.dao.UserDao;
import com.lawencon.admin.dao.VacancyDescriptionDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.jobvacancy.InsertJobVacancyReqDto;
import com.lawencon.admin.model.AgeVacancy;
import com.lawencon.admin.model.AvailableStatus;
import com.lawencon.admin.model.City;
import com.lawencon.admin.model.Company;
import com.lawencon.admin.model.Degree;
import com.lawencon.admin.model.ExperienceLevel;
import com.lawencon.admin.model.Gender;
import com.lawencon.admin.model.JobType;
import com.lawencon.admin.model.JobVacancy;
import com.lawencon.admin.model.User;
import com.lawencon.admin.model.VacancyDescription;
import com.lawencon.base.ConnHandler;
import com.lawencon.security.principal.PrincipalService;
import com.lawencon.security.principal.PrincipalServiceImpl;

public class JobVacancyService {

	@Autowired
	private JobVacancyDao jobVacancyDao;
	
	@Autowired
	private VacancyDescriptionDao vacancyDescriptionDao;
	
	@Autowired
	private AgeVacancyDao ageVacancyDao;
	
	@Autowired
	private CityDao cityDao;
	
	@Autowired
	private DegreeDao degreeDao;
	
	@Autowired
	private GenderDao genderDao;
	
	@Autowired
	private JobTypeDao jobTypeDao;
	
	@Autowired
	private AvailableStatusDao availableStatusDao;
	
	@Autowired
	private CompanyDao companyDao;
	
	@Autowired
	private ExperienceLevelDao experienceLevelDao;
	
	@Autowired
	private UserDao userDao;
	
	private InsertResDto createJob(InsertJobVacancyReqDto data) {
		final InsertResDto response = new InsertResDto();
		
		ConnHandler.begin();
		try {
			final VacancyDescription vacancyDescription = new VacancyDescription();
			vacancyDescription.setAddress(data.getVacancyDescription().getAddress());
			
			final AgeVacancy ageVacancy = ageVacancyDao.getByIdRef(data.getVacancyDescription().getAgeVacancyId());
			vacancyDescription.setAgeVacancy(ageVacancy);
			
			final City city = cityDao.getByIdRef(data.getVacancyDescription().getCityId());
			vacancyDescription.setCity(city);
			
			final Degree degree = degreeDao.getByIdRef(data.getVacancyDescription().getDegreeId());
			vacancyDescription.setDegree(degree);
			
			final Gender gender = genderDao.getByIdRef(data.getVacancyDescription().getGenderId());
			vacancyDescription.setGender(gender);
			
			final JobType jobType = jobTypeDao.getByIdRef(data.getVacancyDescription().getJobTypeId());
			vacancyDescription.setJobType(jobType);
			
			vacancyDescription.setDescription(data.getVacancyDescription().getDescription());
			vacancyDescription.setSalary(data.getVacancyDescription().getSalary());
			
			final VacancyDescription insertedDescription = vacancyDescriptionDao.saveAndFlush(vacancyDescription);
			
			final JobVacancy jobVacancy = new JobVacancy();
			jobVacancy.setVacancyCode(data.getVacancyCode());
			jobVacancy.setVacancyTitle(data.getVacancyTitle());
			jobVacancy.setVacancyDescription(insertedDescription);
			
			final AvailableStatus availableStatus = availableStatusDao.getByIdRef(data.getAvailableStatusId());
			jobVacancy.setAvailableStatus(availableStatus);
			
//			final Company company = companyDao.getByIdRef(PrincipalServiceImpl)
//			jobVacancy.setCompany(company);
			
			final ExperienceLevel experienceLevel = experienceLevelDao.getByIdRef(data.getExpLevelId());
			jobVacancy.setExpLevel(experienceLevel);
			
			final User HRPic = userDao.getByIdRef(data.getPicHrId());
			jobVacancy.setPicHr(HRPic);
			
			final User userPic = userDao.getByIdRef(data.getPicUserId());
			jobVacancy.setPicUser(userPic);

			jobVacancy.setCandidateTotal(0L);
			jobVacancy.setStartDate(data.getStartDate());
			jobVacancy.setEndDate(data.getEndDate());
			
			final JobVacancy insertedJob = jobVacancyDao.save(jobVacancy);
			
			response.setId(insertedJob.getId());
			response.setMessage("Job Vacancy created successfully");
			
			ConnHandler.commit();
		} catch (Exception e) {
			ConnHandler.rollback();
		}
		
		return response;
	}
}
