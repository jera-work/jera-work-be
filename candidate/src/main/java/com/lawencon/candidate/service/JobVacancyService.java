package com.lawencon.candidate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.dao.AgeVacancyDao;
import com.lawencon.candidate.dao.AvailableStatusDao;
import com.lawencon.candidate.dao.CityDao;
import com.lawencon.candidate.dao.CompanyDao;
import com.lawencon.candidate.dao.DegreeDao;
import com.lawencon.candidate.dao.ExperienceLevelDao;
import com.lawencon.candidate.dao.GenderDao;
import com.lawencon.candidate.dao.JobTypeDao;
import com.lawencon.candidate.dao.JobVacancyDao;
import com.lawencon.candidate.dao.VacancyDescriptionDao;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.jobvacancy.InsertJobVacancyReqDto;
import com.lawencon.candidate.dto.vacancydescription.InsertVacancyDescriptionReqDto;
import com.lawencon.candidate.model.JobVacancy;
import com.lawencon.candidate.model.VacancyDescription;

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
	
	public InsertResDto insertJob(InsertJobVacancyReqDto data, InsertVacancyDescriptionReqDto descData) {
		ConnHandler.begin();
		
		final VacancyDescription desc = new VacancyDescription();
		desc.setAddress(descData.getAddress());
		desc.setAgeVacancy(ageDao.getByIdRef(descData.getAgeVacancyId()));
		desc.setCity(cityDao.getByIdRef(descData.getCityId()));
//		desc.setCreatedBy(null);
		desc.setDegree(degreeDao.getByIdRef(descData.getDegreeId()));
		desc.setDescription(descData.getDescription());
		desc.setGender(genderDao.getByIdRef(descData.getGenderId()));
		desc.setJobType(typeDao.getByIdRef(descData.getJobTypeId()));
		desc.setSalary(descData.getSalary());
		
		final VacancyDescription descDb = descDao.save(desc);
		
		final JobVacancy job = new JobVacancy();
		job.setAvailableStatus(statusDao.getByIdRef(data.getAvailableStatusId()));
//		job.setCandidateTotal(data.get);
		job.setCompany(companyDao.getByIdRef(data.getCompanyId()));
		job.setEndDate(data.getEndDate());
		job.setExpLevel(levelDao.getByIdRef(data.getExpLevelId()));
		job.setStartDate(data.getStartDate());
		job.setVacancyCode(data.getVacancyCode());
		job.setVacancyTitle(data.getVacancyTitle());
//		job.setCreatedBy(princi);
		job.setVacancyDescription(descDb);
		
		final JobVacancy jobDb = jobDao.save(job);
		
		final InsertResDto response = new InsertResDto();
		response.setId(jobDb.getId());
		response.setMessage("Job has been created!");
		ConnHandler.commit();
		
		return response;
		
	}

}
