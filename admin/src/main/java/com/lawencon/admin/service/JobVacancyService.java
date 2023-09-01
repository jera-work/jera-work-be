package com.lawencon.admin.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.lawencon.admin.constant.AvailableStatusCode;
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
import com.lawencon.admin.dto.UpdateResDto;
import com.lawencon.admin.dto.email.EmailReqDto;
import com.lawencon.admin.dto.email.ReportReqDto;
import com.lawencon.admin.dto.jobvacancy.InsertJobVacancyReqDto;
import com.lawencon.admin.dto.jobvacancy.JobSearchResDto;
import com.lawencon.admin.dto.jobvacancy.JobVacancyCountAppliedCandidateResDto;
import com.lawencon.admin.dto.jobvacancy.JobVacancyCountLevelResDto;
import com.lawencon.admin.dto.jobvacancy.JobVacancyCountStatusResDto;
import com.lawencon.admin.dto.jobvacancy.JobVacancyResDto;
import com.lawencon.admin.dto.jobvacancy.JobVacancyUpdateReqDto;
import com.lawencon.admin.model.AvailableStatus;
import com.lawencon.admin.model.Company;
import com.lawencon.admin.model.JobVacancy;
import com.lawencon.admin.model.User;
import com.lawencon.admin.model.VacancyDescription;
import com.lawencon.admin.util.DateUtil;
import com.lawencon.base.ConnHandler;
import com.lawencon.security.principal.PrincipalServiceImpl;
import com.lawencon.util.JasperUtil;

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
	@Autowired
	private JasperUtil jasperUtil;
	@Autowired
	private SendMailService sendMailService;

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
			data.setAvailableStatusId(statusDao.getByCode(AvailableStatusCode.OPEN.statusCode).getId());
			job.setAvailableStatus(statusDao.getByIdRef(data.getAvailableStatusId()));

			final User user = userDao.getById(principalService.getAuthPrincipal());
			data.setCompanyId(user.getProfile().getCompany().getId());
			job.setCompany(companyDao.getByIdRef(data.getCompanyId()));
			job.setEndDate(DateUtil.dateTimeParse(data.getEndDate()));
			job.setExpLevel(levelDao.getByIdRef(data.getExpLevelId()));
			job.setStartDate(DateUtil.dateTimeParse(data.getStartDate()));
			job.setVacancyCode(data.getVacancyCode());
			job.setVacancyTitle(data.getVacancyTitle());
			job.setPicHr(userDao.getByIdRef(data.getPicHrId()));
			job.setPicUser(userDao.getByIdRef(data.getPicUserId()));
			job.setVacancyDescription(descDb);
			final JobVacancy jobDb = jobDao.save(job);

			final HttpStatus candidateResponse = apiService.writeTo("http://localhost:8080/jobs", data);

			final InsertResDto response = new InsertResDto();
			if (candidateResponse.equals(HttpStatus.CREATED)) {
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

	public List<JobSearchResDto> filter(int startIndex, int endIndex, String vacancyTitle, String degreeId,
			String cityId, String jobTypeId) {
		final List<JobSearchResDto> responses = new ArrayList<>();

		jobDao.getAllWithLimit(startIndex, endIndex, vacancyTitle, degreeId, cityId, jobTypeId).forEach(jv -> {
			final JobSearchResDto response = new JobSearchResDto();
			response.setId(jv.getId());
			response.setCityName(jv.getVacancyDescription().getCity().getCityName());
			response.setCompanyName(jv.getCompany().getCompanyName());
			response.setDegreeName(jv.getVacancyDescription().getDegree().getDegreeName());
			response.setJobTypeName(jv.getVacancyDescription().getJobType().getTypeName());
			response.setSalary(jv.getVacancyDescription().getSalary());
			response.setVacancyTitle(jv.getVacancyTitle());
			response.setVacancyCode(jv.getVacancyCode());
			response.setCompanyPhotoId(jv.getCompany().getPhoto().getId());
			response.setCreatedAt(DateUtil.dateTimeFormatIso(jv.getCreatedAt()));

			responses.add(response);
		});

		return responses;
	}

	public List<JobSearchResDto> getAllWithPagination(int startIndex, int endIndex) {
		final List<JobSearchResDto> responses = new ArrayList<>();

		jobDao.getAll(startIndex, endIndex).forEach(jv -> {
			final JobSearchResDto response = new JobSearchResDto();
			response.setId(jv.getId());
			response.setCityName(jv.getVacancyDescription().getCity().getCityName());
			response.setCompanyName(jv.getCompany().getCompanyName());
			response.setDegreeName(jv.getVacancyDescription().getDegree().getDegreeName());
			response.setJobTypeName(jv.getVacancyDescription().getJobType().getTypeName());
			response.setSalary(jv.getVacancyDescription().getSalary());
			response.setVacancyTitle(jv.getVacancyTitle());
			response.setVacancyCode(jv.getVacancyCode());
			response.setCompanyPhotoId(jv.getCompany().getPhoto().getId());
			response.setCreatedAt(DateUtil.dateTimeFormatIso(jv.getCreatedAt()));

			responses.add(response);
		});

		return responses;
	}

	public List<JobSearchResDto> getAll() {
		final List<JobSearchResDto> responses = new ArrayList<>();

		jobDao.getAll().forEach(jv -> {
			final JobSearchResDto response = new JobSearchResDto();
			response.setId(jv.getId());
			response.setCityName(jv.getVacancyDescription().getCity().getCityName());
			response.setCompanyName(jv.getCompany().getCompanyName());
			response.setDegreeName(jv.getVacancyDescription().getDegree().getDegreeName());
			response.setJobTypeName(jv.getVacancyDescription().getJobType().getTypeName());
			response.setSalary(jv.getVacancyDescription().getSalary());
			response.setVacancyTitle(jv.getVacancyTitle());
			response.setVacancyCode(jv.getVacancyCode());
			response.setCompanyPhotoId(jv.getCompany().getPhoto().getId());
			response.setCreatedAt(DateUtil.dateTimeFormatIso(jv.getCreatedAt()));

			responses.add(response);
		});

		return responses;
	}

	public List<JobSearchResDto> latestJob(int startIndex, int endIndex) {
		final List<JobSearchResDto> responses = new ArrayList<>();

		jobDao.getLatestJob(startIndex, endIndex).forEach(jv -> {
			final JobSearchResDto response = new JobSearchResDto();
			response.setId(jv.getId());
			response.setCityName(jv.getVacancyDescription().getCity().getCityName());
			response.setCompanyName(jv.getCompany().getCompanyName());
			response.setDegreeName(jv.getVacancyDescription().getDegree().getDegreeName());
			response.setJobTypeName(jv.getVacancyDescription().getJobType().getTypeName());
			response.setSalary(jv.getVacancyDescription().getSalary());
			response.setVacancyTitle(jv.getVacancyTitle());
			response.setVacancyCode(jv.getVacancyCode());
			response.setCompanyPhotoId(jv.getCompany().getPhoto().getId());
			response.setCreatedAt(DateUtil.dateTimeFormatIso(jv.getCreatedAt()));

			responses.add(response);
		});

		return responses;
	}

	public List<JobVacancyResDto> jobByCompany() {
		final List<JobVacancyResDto> responses = new ArrayList<>();

		final User user = userDao.getById(principalService.getAuthPrincipal());
		final Company company = companyDao.getById(user.getProfile().getCompany().getId());
		final List<JobVacancy> jobs = jobDao.getJobByCompany(company.getId());
		
		jobs.forEach(jv -> {
			final JobVacancyResDto response = new JobVacancyResDto();
			response.setCompanyName(jv.getCompany().getCompanyName());
			response.setEndDate(DateUtil.dateTimeFormat(jv.getEndDate()));
			response.setStartDate(DateUtil.dateTimeFormat(jv.getStartDate()));
			response.setHrName(jv.getPicHr().getProfile().getProfileName());
			response.setUserName(jv.getPicUser().getProfile().getProfileName());
			response.setLevelName(jv.getExpLevel().getLevelName());
			response.setStatusName(changeStatusByEndDate(jv.getId()));
			response.setVacancyTitle(jv.getVacancyTitle());
			response.setVacancyCode(jv.getVacancyCode());
			response.setVacancyId(jv.getId());

			responses.add(response);
		});

		return responses;
	}

	public JobVacancyResDto getJobDetail(String jobId) {
		final JobVacancy jv = jobDao.getById(jobId);
		final VacancyDescription vd = descDao.getById(jv.getVacancyDescription().getId());

		final JobVacancyResDto response = new JobVacancyResDto();
		response.setCompanyName(jv.getCompany().getCompanyName());
		response.setCompanyDesc(jv.getCompany().getDescription());
		response.setCompanyPhotoId(jv.getCompany().getPhoto().getId());
		response.setDegreeId(vd.getDegree().getId());
		response.setDegreeName(vd.getDegree().getDegreeName());
		response.setGenderId(vd.getGender().getId());
		response.setGenderName(vd.getGender().getGenderName());
		response.setAgeVacancyId(vd.getAgeVacancy().getId());
		response.setAgeVacancyName(vd.getAgeVacancy().getAgeName());
		response.setJobTypeId(vd.getJobType().getId());
		response.setJobTypeName(vd.getJobType().getTypeName());
		response.setSalary(vd.getSalary());
		response.setCityId(vd.getCity().getId());
		response.setCityName(vd.getCity().getCityName());
		response.setAddress(vd.getAddress());
		response.setDescription(vd.getDescription());
		response.setEndDate(DateUtil.dateTimeFormat(jv.getEndDate()));
		response.setStartDate(DateUtil.dateTimeFormat(jv.getStartDate()));
		response.setHrId(jv.getPicHr().getId());
		response.setHrName(jv.getPicHr().getProfile().getProfileName());
		response.setUserId(jv.getPicUser().getId());
		response.setUserName(jv.getPicUser().getProfile().getProfileName());
		response.setLevelId(jv.getExpLevel().getId());
		response.setLevelName(jv.getExpLevel().getLevelName());
		response.setStatusId(jv.getAvailableStatus().getId());
		response.setStatusName(jv.getAvailableStatus().getStatusname());
		response.setVacancyTitle(jv.getVacancyTitle());
		response.setVacancyCode(jv.getVacancyCode());
		response.setVacancyId(jv.getId());

		return response;
	}

	public JobSearchResDto getByCode(String code) {
		final JobVacancy job = jobDao.getByCode(code);
		final JobVacancy jv = jobDao.getById(job.getId());

		final JobSearchResDto response = new JobSearchResDto();
		response.setId(jv.getId());
		response.setCityName(jv.getVacancyDescription().getCity().getCityName());
		response.setCompanyName(jv.getCompany().getCompanyName());
		response.setDegreeName(jv.getVacancyDescription().getDegree().getDegreeName());
		response.setJobTypeName(jv.getVacancyDescription().getJobType().getTypeName());
		response.setSalary(jv.getVacancyDescription().getSalary());
		response.setVacancyTitle(jv.getVacancyTitle());
		response.setVacancyCode(jv.getVacancyCode());
		response.setCompanyPhotoId(jv.getCompany().getPhoto().getId());
		response.setCreatedAt(DateUtil.dateTimeFormatIso(jv.getCreatedAt()));

		return response;
	}
	
	public InsertResDto getReport() {
		final InsertResDto response = new InsertResDto();
		final List<JobVacancyResDto> jobVacancies = new ArrayList<>();

		final User user = userDao.getById(principalService.getAuthPrincipal());
		final Company company = companyDao.getById(user.getProfile().getCompany().getId());

		jobDao.getJobByCompany(company.getId()).forEach(jv -> {
			final JobVacancyResDto jobVacancy = new JobVacancyResDto();
			jobVacancy.setCompanyName(jv.getCompany().getCompanyName());
			jobVacancy.setEndDate(DateUtil.dateTimeFormat(jv.getEndDate()));
			jobVacancy.setStartDate(DateUtil.dateTimeFormat(jv.getStartDate()));
			jobVacancy.setHrName(jv.getPicHr().getProfile().getProfileName());
			jobVacancy.setUserName(jv.getPicUser().getProfile().getProfileName());
			jobVacancy.setLevelName(jv.getExpLevel().getLevelName());
			jobVacancy.setStatusName(jv.getAvailableStatus().getStatusname());
			jobVacancy.setVacancyTitle(jv.getVacancyTitle());
			jobVacancy.setVacancyCode(jv.getVacancyCode());
			jobVacancy.setVacancyId(jv.getId());
			jobVacancy.setAppliedCandidateTotal(jobDao.getAppliedCandidateTotal(jv.getId()));

			jobVacancies.add(jobVacancy);
		});
		
		final List<JobVacancyCountLevelResDto> jobsCountLevel = new ArrayList<>();
        
        for(int i = 0; i < jobVacancies.size(); i++) {
        	JobVacancyCountLevelResDto jobCountLevel = new JobVacancyCountLevelResDto();
        	jobCountLevel.setLevelName(jobVacancies.get(i).getLevelName());
        	jobCountLevel.setLevelCount(1);
        	
        	boolean levelExist = false;
        	for(JobVacancyCountLevelResDto j : jobsCountLevel) {
        		if(j.getLevelName().equals(jobVacancies.get(i).getLevelName())) {
        			levelExist = true;
        		}
        	}
        	
        	if(!levelExist) {
        		jobsCountLevel.add(jobCountLevel);
        	}else {
        		for(int j = 0; j < jobsCountLevel.size(); j++) {
        			if(jobsCountLevel.get(j).getLevelName().equals(jobVacancies.get(i).getLevelName())) {
        				jobCountLevel.setLevelCount(jobsCountLevel.get(j).getLevelCount()+1);
        				jobsCountLevel.set(j, jobCountLevel);
        			}
        		}
        	}
        	
        }
        
        final List<JobVacancyCountStatusResDto> jobsCountStatus = new ArrayList<>();
        
        for(int i = 0; i < jobVacancies.size(); i++) {
        	final JobVacancyCountStatusResDto jobCountStatus = new JobVacancyCountStatusResDto();
        	jobCountStatus.setStatusName(jobVacancies.get(i).getStatusName());
        	jobCountStatus.setStatusCount(1);
        	
        	boolean statusExist = false;
        	for(JobVacancyCountStatusResDto j : jobsCountStatus) {
        		if(j.getStatusName().equals(jobVacancies.get(i).getStatusName())) {
        			statusExist = true;
        		}
        	}
        	
        	if(!statusExist) {
        		jobsCountStatus.add(jobCountStatus);
        	}else {
        		for(int j = 0; j < jobsCountStatus.size(); j++) {
        			if(jobsCountStatus.get(j).getStatusName().equals(jobVacancies.get(i).getStatusName())) {
        				jobCountStatus.setStatusCount(jobsCountStatus.get(j).getStatusCount()+1);
        				jobsCountStatus.set(j, jobCountStatus);
        			}
        		}
        	}
        }
        
        final List<JobVacancyCountAppliedCandidateResDto> jobsCountApplied = new ArrayList<>();
        
        for(int i = 0; i < jobVacancies.size(); i++) {
        	final JobVacancyCountAppliedCandidateResDto jobCountApplied = new JobVacancyCountAppliedCandidateResDto();
        	jobCountApplied.setJobName(jobVacancies.get(i).getVacancyTitle() + " - " + jobVacancies.get(i).getLevelName());
        	jobCountApplied.setAppliedCount(jobVacancies.get(i).getAppliedCandidateTotal());
        	
        	boolean jobExist = false;
        	for(JobVacancyCountAppliedCandidateResDto j : jobsCountApplied) {
        		if(j.getJobName().equals(jobVacancies.get(i).getVacancyTitle())) {
        			jobExist = true;
        		}
        	}
        	
        	if(!jobExist) {
        		jobsCountApplied.add(jobCountApplied);
        	}else {
        		for(int j = 0; j < jobsCountApplied.size(); j++) {
        			if(jobsCountApplied.get(j).getJobName().equals(jobVacancies.get(i).getVacancyTitle() + " - " + jobVacancies.get(i).getLevelName())) {
        				jobCountApplied.setAppliedCount(jobsCountApplied.get(j).getAppliedCount());
        				jobsCountApplied.set(j, jobCountApplied);
        			}
        		}
        	}
        	
        }
        
        final Collection<List<JobVacancyResDto>> result = new ArrayList<>();
        result.add(jobVacancies);
        
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("jobVacancies", jobVacancies);
        parameters.put("expLevels", jobsCountLevel);
        parameters.put("availableStatuses", jobsCountStatus);
        parameters.put("appliedCandidates", jobsCountApplied);
        
        try {				
        	byte[] dataOut = jasperUtil.responseToByteArray(result, parameters, "jasper-job-vacancies");
                	
	        final EmailReqDto emailReqDto = new EmailReqDto();
			emailReqDto.setSubject("Job Vacancies Report");
			emailReqDto.setEmail(user.getUserEmail());
			
			final ReportReqDto reportReqDto = new ReportReqDto();
			reportReqDto.setHeader("Job Vacancy List Report");
			reportReqDto.setFullName(user.getProfile().getProfileName());
			reportReqDto.setCompanyName(company.getCompanyName());
			reportReqDto.setCreatedAt(DateUtil.dateTimeFormat(LocalDateTime.now()));
			
			sendMailService.sendJobReport(emailReqDto, reportReqDto, dataOut);
			            
			response.setMessage("Report created successfully");
			return response;		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public UpdateResDto editJob(JobVacancyUpdateReqDto data) {
		UpdateResDto response = new UpdateResDto();
		final User user = userDao.getById(principalService.getAuthPrincipal());
		final Company company = companyDao.getById(user.getProfile().getCompany().getId());
		
		try {
			ConnHandler.begin();
			final JobVacancy job = jobDao.getById(data.getJobVacancyId());
			final VacancyDescription desc = descDao.getById(job.getVacancyDescription().getId());
			
			desc.setAddress(data.getAddress());
			desc.setAgeVacancy(ageDao.getByIdRef(data.getAgeVacancyId()));
			desc.setCity(cityDao.getByIdRef(data.getCityId()));
			desc.setDegree(degreeDao.getByIdRef(data.getDegreeId()));
			desc.setDescription(data.getDescription());
			desc.setGender(genderDao.getByIdRef(data.getGenderId()));
			desc.setJobType(typeDao.getByIdRef(data.getJobTypeId()));
			desc.setSalary(data.getSalary());
			final VacancyDescription descDb = descDao.saveAndFlush(desc);
			
			final AvailableStatus status = statusDao.getById(data.getAvailableStatusId());
			if(DateUtil.dateTimeParse(data.getEndDate()).isBefore(LocalDateTime.now())) {
				final AvailableStatus statusClose = statusDao.getByCode("CLS");
				job.setAvailableStatus(statusDao.getByIdRef(statusClose.getId()));
				job.setEndDate(LocalDateTime.now());
				data.setEndDate(DateUtil.dateTimeFormat(LocalDateTime.now()));
				data.setAvailableStatusId(statusClose.getId());
			} else if(status.getStatusCode().equals("CLS")) {
				job.setAvailableStatus(status);
				data.setAvailableStatusId(status.getId());
			} else {
				final AvailableStatus statusOpen = statusDao.getByCode("OPN");
				job.setAvailableStatus(statusDao.getByIdRef(statusOpen.getId()));
				job.setEndDate(DateUtil.dateTimeParse(data.getEndDate()));
				data.setAvailableStatusId(statusOpen.getId());
			}
			
			job.setCompany(companyDao.getByIdRef(company.getId()));
			job.setExpLevel(levelDao.getByIdRef(data.getExpLevelId()));
			job.setPicHr(userDao.getByIdRef(data.getPicHrId()));
			job.setPicUser(userDao.getByIdRef(data.getPicUserId()));
			job.setStartDate(DateUtil.dateTimeParse(data.getStartDate()));
			job.setVacancyCode(data.getVacancyCode());
			job.setVacancyDescription(descDb);
			job.setVacancyTitle(data.getVacancyTitle());
			final JobVacancy jobDb = jobDao.saveAndFlush(job);
			
			data.setVacancyCode(job.getVacancyCode());
			apiService.putTo("http://localhost:8080/jobs/edit", data);
			ConnHandler.commit();
			
			response.setMessage("Job has been edited!");
			response.setVer(jobDb.getVersion());
		} catch (Exception e) {
			e.printStackTrace();
			ConnHandler.rollback();
		}
		
		return response;
	}
	
	/* Change available status according job's end date. Used only in jobByCompany() */	
	public String changeStatusByEndDate(String jobId) {
		ConnHandler.begin();
		
		final JobVacancy job = jobDao.getById(jobId);
		if(job.getEndDate().isBefore(LocalDateTime.now()) || job.getEndDate().isEqual(LocalDateTime.now())) {
			final AvailableStatus statusClose = statusDao.getByCode("CLS");
			job.setAvailableStatus(statusClose);
			final JobVacancy jobDb = jobDao.saveAndFlush(job);
			ConnHandler.commit();
			
			return statusDao.getById(jobDb.getAvailableStatus().getId()).getStatusname();
		} else {
			ConnHandler.rollback();
			return statusDao.getById(job.getAvailableStatus().getId()).getStatusname();
		}
		
	}
	
}