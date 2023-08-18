package com.lawencon.candidate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.dao.JobVacancyDao;
import com.lawencon.candidate.dao.VacancyDescriptionDao;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.jobvacancy.InsertJobVacancyReqDto;
import com.lawencon.candidate.model.JobVacancy;
import com.lawencon.candidate.model.VacancyDescription;

@Service
public class JobVacancyService {

	@Autowired
	private VacancyDescriptionDao descDao;
	@Autowired
	private JobVacancyDao jobDao;

	public InsertResDto insertJob(InsertJobVacancyReqDto data) {
		ConnHandler.begin();

		try {
			final VacancyDescription desc = new VacancyDescription();
			desc.setAddress(data.getAddress());
			desc.setAgeVacancy(data.getAgeVacancyId());
			desc.setCity(data.getCityId());
			desc.setDegree(data.getDegreeId());
			desc.setDescription(data.getDescription());
			desc.setGender(data.getGenderId());
			desc.setJobType(data.getJobTypeId());
			desc.setSalary(data.getSalary());

			final VacancyDescription descDb = descDao.saveAndFlush(desc);

			final JobVacancy job = new JobVacancy();
			job.setAvailableStatus(data.getAvailableStatusId());
			job.setCompany(data.getCompanyId());
			job.setEndDate(data.getEndDate());
			job.setExpLevel(data.getExpLevelId());
			job.setStartDate(data.getStartDate());
			job.setVacancyCode(data.getVacancyCode());
			job.setVacancyTitle(data.getVacancyTitle());
			job.setVacancyDescription(descDb);

			final JobVacancy jobDb = jobDao.save(job);

			final InsertResDto response = new InsertResDto();
			response.setId(jobDb.getId());
			response.setMessage("Job has been created!");
			ConnHandler.commit();

			return response;
		} catch (Exception e) {
			ConnHandler.rollback();
			return null;
		}
	}

}
