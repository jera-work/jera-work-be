package com.lawencon.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
import com.lawencon.admin.model.JobVacancy;
import com.lawencon.admin.model.User;
import com.lawencon.admin.model.VacancyDescription;
import com.lawencon.base.ConnHandler;
import com.lawencon.security.principal.PrincipalServiceImpl;

@Service
public class JobVacancyService {

	@Autowired
	private AvailableStatusDao statusDao;
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private ExperienceLevelDao levelDao;
	@Autowired
	private AgeVacancyDao ageDao;
	@Autowired
	private CityDao cityDao;
	@Autowired
	private DegreeDao degreeDao;
	@Autowired
	private GenderDao genderDao;
	@Autowired
	private JobTypeDao typeDao;
	@Autowired
	private VacancyDescriptionDao descDao;
	@Autowired
	private JobVacancyDao jobDao;
	@Autowired
	private PrincipalServiceImpl principalService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ApiService apiService;

	public InsertResDto insertJob(InsertJobVacancyReqDto data) {
		
		try {
			ConnHandler.begin();
			final VacancyDescription desc = new VacancyDescription();
			desc.setAddress(data.getAddress());
			desc.setAgeVacancy(ageDao.getByIdRef(data.getAgeVacancyId()));
			desc.setCity(cityDao.getByIdRef(data.getCityId()));
			desc.setDegree(degreeDao.getByIdRef(data.getDegreeId()));
			desc.setDescription(data.getDescription());
			desc.setGender(genderDao.getByIdRef(data.getGenderId()));
			desc.setJobType(typeDao.getByIdRef(data.getJobTypeId()));
			desc.setSalary(data.getSalary());
			final VacancyDescription descDb = descDao.saveAndFlush(desc);

			final JobVacancy job = new JobVacancy();
			job.setAvailableStatus(statusDao.getByIdRef(data.getAvailableStatusId()));

			final User user = userDao.getById(principalService.getAuthPrincipal());
			job.setCompany(companyDao.getByIdRef(user.getProfile().getCompany().getId()));
			job.setEndDate(data.getEndDate());
			job.setExpLevel(levelDao.getByIdRef(data.getExpLevelId()));
			job.setStartDate(data.getStartDate());
			job.setVacancyCode(data.getVacancyCode());
			job.setVacancyTitle(data.getVacancyTitle());
			job.setPicHr(userDao.getByIdRef(data.getPicHrId()));
			job.setPicUser(userDao.getByIdRef(data.getPicUserId()));
			job.setVacancyDescription(descDb);
			final JobVacancy jobDb = jobDao.save(job);

			final HttpStatus candidateResponse = apiService.writeTo("http://localhost:8080/jobs", data);

			final InsertResDto response = new InsertResDto();
			if(candidateResponse.equals(HttpStatus.CREATED)) {
				response.setId(jobDb.getId());
				response.setMessage("Job has been created!");	
				ConnHandler.commit();
			} else {
				ConnHandler.rollback();
				
				throw new RuntimeException("Insert Failed");
			}
			return response;

		} catch (Exception e) {
			e.printStackTrace();
			ConnHandler.rollback();
			return null;
		}

	}
}

