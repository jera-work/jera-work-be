package com.lawencon.candidate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.dao.JobVacancyDao;
import com.lawencon.candidate.dao.VacancyDescriptionDao;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.UpdateResDto;
import com.lawencon.candidate.dto.jobvacancy.InsertJobVacancyReqDto;
import com.lawencon.candidate.dto.jobvacancy.JobSearchResDto;
import com.lawencon.candidate.dto.jobvacancy.JobVacancyResDto;
import com.lawencon.candidate.dto.jobvacancy.JobVacancyUpdateReqDto;
import com.lawencon.candidate.model.JobVacancy;
import com.lawencon.candidate.model.VacancyDescription;
import com.lawencon.candidate.util.DateUtil;

@Service
public class JobVacancyService {

	@Autowired
	private VacancyDescriptionDao descDao;
	@Autowired
	private JobVacancyDao jobDao;
	@Autowired
	private ApiService apiService;

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
			job.setEndDate(DateUtil.dateTimeParse(data.getEndDate()));
			job.setExpLevel(data.getExpLevelId());
			job.setStartDate(DateUtil.dateTimeParse(data.getStartDate()));
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

	public List<JobSearchResDto> filter(int startIndex, int endIndex, String vacancyTitle, String degreeId,
			String cityId, String jobTypeId) {
		final String url = "http://localhost:8081/jobs/search/" + "?startIndex=" + startIndex + "&endIndex=" + endIndex
				+ "&degreeId=" + degreeId + "&vacancyTitle=" + vacancyTitle + "&cityId=" + cityId + "&jobTypeId="
				+ jobTypeId;

		final List<JobSearchResDto> responseFromAdmins = apiService.getListFrom(url, JobSearchResDto.class);
		final List<JobSearchResDto> responseFromAdminConverteds = new ObjectMapper().convertValue(responseFromAdmins,
				new TypeReference<List<JobSearchResDto>>() {
				});

		final List<JobSearchResDto> jobFromCandidates = new ArrayList<>();
		jobDao.getAll().forEach(jv -> {
			final JobSearchResDto jobFromCandidateRes = new JobSearchResDto();
			jobFromCandidateRes.setId(jv.getId());
			jobFromCandidateRes.setVacancyCode(jv.getVacancyCode());

			jobFromCandidates.add(jobFromCandidateRes);
		});

		final List<JobSearchResDto> responses = new ArrayList<>();

		responseFromAdminConverteds.forEach(resCon -> {
			jobFromCandidates.forEach(jobCdt -> {
				if (resCon.getVacancyCode().equals(jobCdt.getVacancyCode())) {
					jobCdt.setCityName(resCon.getCityName());
					jobCdt.setCompanyName(resCon.getCompanyName());
					jobCdt.setCompanyPhotoId(resCon.getCompanyPhotoId());
					jobCdt.setDegreeName(resCon.getDegreeName());
					jobCdt.setJobTypeName(resCon.getJobTypeName());
					jobCdt.setSalary(resCon.getSalary());
					jobCdt.setVacancyTitle(resCon.getVacancyTitle());
					jobCdt.setCreatedAt(resCon.getCreatedAt());
					final JobSearchResDto response = jobCdt;

					responses.add(response);
				}
			});
		});

		return responses;
	}

	public List<JobSearchResDto> getAllWithPagination(int startIndex, int endIndex) {
		final String url = "http://localhost:8081/jobs/page/" + "?startIndex=" + startIndex + "&endIndex=" + endIndex;

		final List<JobSearchResDto> responseFromAdmins = apiService.getListFrom(url, JobSearchResDto.class);
		final List<JobSearchResDto> responseFromAdminConverteds = new ObjectMapper().convertValue(responseFromAdmins,
				new TypeReference<List<JobSearchResDto>>() {
				});

		final List<JobSearchResDto> jobFromCandidates = new ArrayList<>();
		jobDao.getAll().forEach(jv -> {
			final JobSearchResDto jobFromCandidateRes = new JobSearchResDto();
			jobFromCandidateRes.setId(jv.getId());
			jobFromCandidateRes.setVacancyCode(jv.getVacancyCode());

			jobFromCandidates.add(jobFromCandidateRes);
		});

		final List<JobSearchResDto> responses = new ArrayList<>();

		responseFromAdminConverteds.forEach(resCon -> {
			jobFromCandidates.forEach(jobCdt -> {
				if (resCon.getVacancyCode().equals(jobCdt.getVacancyCode())) {
					jobCdt.setCityName(resCon.getCityName());
					jobCdt.setCompanyName(resCon.getCompanyName());
					jobCdt.setCompanyPhotoId(resCon.getCompanyPhotoId());
					jobCdt.setDegreeName(resCon.getDegreeName());
					jobCdt.setJobTypeName(resCon.getJobTypeName());
					jobCdt.setSalary(resCon.getSalary());
					jobCdt.setVacancyTitle(resCon.getVacancyTitle());
					jobCdt.setCreatedAt(resCon.getCreatedAt());
					final JobSearchResDto response = jobCdt;

					responses.add(response);
				}
			});
		});

		return responses;
	}

	public List<JobSearchResDto> getAll() {
		final String url = "http://localhost:8081/jobs";
		final List<JobSearchResDto> responseFromAdmins = apiService.getListFrom(url, JobSearchResDto.class);
		final List<JobSearchResDto> responseFromAdminConverteds = new ObjectMapper().convertValue(responseFromAdmins,
				new TypeReference<List<JobSearchResDto>>() {
				});

		final List<JobSearchResDto> jobFromCandidates = new ArrayList<>();
		jobDao.getAll().forEach(jv -> {
			final JobSearchResDto jobFromCandidateRes = new JobSearchResDto();
			jobFromCandidateRes.setId(jv.getId());
			jobFromCandidateRes.setVacancyCode(jv.getVacancyCode());
			jobFromCandidateRes.setCreatedAt(DateUtil.dateTimeFormat(jv.getCreatedAt()));

			jobFromCandidates.add(jobFromCandidateRes);
		});

		final List<JobSearchResDto> responses = new ArrayList<>();

		responseFromAdminConverteds.forEach(resCon -> {
			jobFromCandidates.forEach(jobCdt -> {
				if (resCon.getVacancyCode().equals(jobCdt.getVacancyCode())) {
					jobCdt.setCityName(resCon.getCityName());
					jobCdt.setCompanyName(resCon.getCompanyName());
					jobCdt.setCompanyPhotoId(resCon.getCompanyPhotoId());
					jobCdt.setDegreeName(resCon.getDegreeName());
					jobCdt.setJobTypeName(resCon.getJobTypeName());
					jobCdt.setSalary(resCon.getSalary());
					jobCdt.setVacancyTitle(resCon.getVacancyTitle());
					final JobSearchResDto response = jobCdt;

					responses.add(response);
				}
			});
		});

		return responses;
	}

	public List<JobSearchResDto> latestJob(int startIndex, int endIndex) {
		final String url = "http://localhost:8081/jobs/latest/" + "?startIndex=" + startIndex + "&endIndex=" + endIndex;
		final List<JobSearchResDto> responseFromAdmins = apiService.getListFrom(url, JobSearchResDto.class);
		final List<JobSearchResDto> responseFromAdminConverteds = new ObjectMapper().convertValue(responseFromAdmins,
				new TypeReference<List<JobSearchResDto>>() {
				});

		final List<JobSearchResDto> jobFromCandidates = new ArrayList<>();
		jobDao.getAll().forEach(jv -> {
			final JobSearchResDto jobFromCandidateRes = new JobSearchResDto();
			jobFromCandidateRes.setId(jv.getId());
			jobFromCandidateRes.setVacancyCode(jv.getVacancyCode());
			jobFromCandidateRes.setCreatedAt(DateUtil.dateTimeFormat(jv.getCreatedAt()));

			jobFromCandidates.add(jobFromCandidateRes);
		});

		final List<JobSearchResDto> responses = new ArrayList<>();

		responseFromAdminConverteds.forEach(resCon -> {
			jobFromCandidates.forEach(jobCdt -> {
				if (resCon.getVacancyCode().equals(jobCdt.getVacancyCode())) {
					jobCdt.setCityName(resCon.getCityName());
					jobCdt.setCompanyName(resCon.getCompanyName());
					jobCdt.setCompanyPhotoId(resCon.getCompanyPhotoId());
					jobCdt.setDegreeName(resCon.getDegreeName());
					jobCdt.setJobTypeName(resCon.getJobTypeName());
					jobCdt.setSalary(resCon.getSalary());
					jobCdt.setVacancyTitle(resCon.getVacancyTitle());
					final JobSearchResDto response = jobCdt;

					responses.add(response);
				}
			});
		});

		return responses;
	}

	public JobVacancyResDto getJobDetail(String jobId) {
		final JobVacancy job = jobDao.getById(jobId);

		final String urlToGetId = "http://localhost:8081/jobs/code/?code=" + job.getVacancyCode();
		final JobSearchResDto jobIdFromAdmin = apiService.getFrom(urlToGetId, JobSearchResDto.class);
		final String url = "http://localhost:8081/jobs/detail/?jobId=" + jobIdFromAdmin.getId();
		final JobVacancyResDto responseFromAdmin = apiService.getFrom(url, JobVacancyResDto.class);

		final JobVacancyResDto response = new JobVacancyResDto();
		response.setCompanyName(responseFromAdmin.getCompanyName());
		response.setCompanyDesc(responseFromAdmin.getCompanyDesc());
		response.setCompanyPhotoId(responseFromAdmin.getCompanyPhotoId());
		response.setDegreeName(responseFromAdmin.getDegreeName());
		response.setGenderName(responseFromAdmin.getGenderName());
		response.setAgeVacancyName(responseFromAdmin.getAgeVacancyName());
		response.setJobTypeName(responseFromAdmin.getJobTypeName());
		response.setSalary(responseFromAdmin.getSalary());
		response.setCityName(responseFromAdmin.getCityName());
		response.setAddress(responseFromAdmin.getAddress());
		response.setDescription(responseFromAdmin.getDescription());
		response.setEndDate(responseFromAdmin.getEndDate());
		response.setStartDate(responseFromAdmin.getStartDate());
		response.setHrName(responseFromAdmin.getHrName());
		response.setUserName(responseFromAdmin.getUserName());
		response.setLevelName(responseFromAdmin.getLevelName());
		response.setStatusName(responseFromAdmin.getStatusName());
		response.setVacancyTitle(responseFromAdmin.getVacancyTitle());
		response.setVacancyId(jobId);
		response.setVacancyCode(job.getVacancyCode());
		return response;
	}
	
	public UpdateResDto editJob(JobVacancyUpdateReqDto data) {
		ConnHandler.begin();

		try {
			final JobVacancy job = jobDao.getByCode(data.getVacancyCode());
			
			final VacancyDescription desc = descDao.getById(job.getVacancyDescription().getId());
			desc.setAddress(data.getAddress());
			desc.setAgeVacancy(data.getAgeVacancyId());
			desc.setCity(data.getCityId());
			desc.setDegree(data.getDegreeId());
			desc.setDescription(data.getDescription());
			desc.setGender(data.getGenderId());
			desc.setJobType(data.getJobTypeId());
			desc.setSalary(data.getSalary());
			final VacancyDescription descDb = descDao.saveAndFlush(desc);

			job.setAvailableStatus(data.getAvailableStatusId());
			job.setCompany(data.getCompanyId());
			job.setEndDate(DateUtil.dateTimeParse(data.getEndDate()));
			job.setExpLevel(data.getExpLevelId());
			job.setStartDate(DateUtil.dateTimeParse(data.getStartDate()));
			job.setVacancyCode(data.getVacancyCode());
			job.setVacancyTitle(data.getVacancyTitle());
			job.setVacancyDescription(descDb);
			final JobVacancy jobDb = jobDao.saveAndFlush(job);

			final UpdateResDto response = new UpdateResDto();
			response.setVer(jobDb.getVersion());
			response.setMessage("Job has been created!");
			ConnHandler.commit();

			return response;
		} catch (Exception e) {
			ConnHandler.rollback();
			return null;
		}
	}
}