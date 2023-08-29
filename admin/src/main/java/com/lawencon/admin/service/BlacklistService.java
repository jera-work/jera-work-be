package com.lawencon.admin.service;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.constant.AppliedProgressCode;
import com.lawencon.admin.dao.AppliedVacancyDao;
import com.lawencon.admin.dao.BlacklistEmployeeDao;
import com.lawencon.admin.dao.CompanyDao;
import com.lawencon.admin.dao.HiredEmployeeDao;
import com.lawencon.admin.dao.JobVacancyDao;
import com.lawencon.admin.dao.UserDao;
import com.lawencon.admin.dao.VacancyDescriptionDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.blacklistemployee.BlacklistEmployeeResDto;
import com.lawencon.admin.dto.blacklistemployee.InsertBlacklistEmployeeReqDto;
import com.lawencon.admin.dto.jobvacancy.JobVacancyResDto;
import com.lawencon.admin.model.AppliedVacancy;
import com.lawencon.admin.model.BlacklistEmployee;
import com.lawencon.admin.model.JobVacancy;
import com.lawencon.admin.model.VacancyDescription;
import com.lawencon.admin.util.DateUtil;
import com.lawencon.base.ConnHandler;
import com.lawencon.security.principal.PrincipalService;

@Service
public class BlacklistService {

	@Autowired
	private PrincipalService<String> principalService;
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private HiredEmployeeDao hiredDao;
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private BlacklistEmployeeDao blacklistDao;
	@Autowired
	private JobVacancyDao jobDao;
	@Autowired
	private AppliedVacancyDao appliedDao;
	@Autowired
	private VacancyDescriptionDao jobDetailDao;
	/* Move employee to blacklist */
	public InsertResDto blacklistEmployee(InsertBlacklistEmployeeReqDto data) {

		ConnHandler.begin();
		final BlacklistEmployee blacklisted = new BlacklistEmployee();
		blacklisted.setEmployee(hiredDao.getByIdRef(data.getEmployeeId()));
		blacklisted.setCompany(companyDao.getByIdRef(userDao.getById(principalService.getAuthPrincipal()).getProfile().getCompany().getId()));
		final BlacklistEmployee blacklistedDb = blacklistDao.saveAndFlush(blacklisted);
		ConnHandler.commit();

		final InsertResDto response = new InsertResDto();
		response.setId(blacklistedDb.getId());
		response.setMessage("Candidate has been blacklisted!");
		return response;

	}

	public List<BlacklistEmployeeResDto> getByCompany(int firstIndex, int lastIndex){
		final List<BlacklistEmployeeResDto> responses = new ArrayList<>();
		
		blacklistDao.getByCompany(
				firstIndex, lastIndex, 
				userDao.getById(principalService.getAuthPrincipal()).getProfile().getCompany().getId())
					.forEach(be -> {
			final BlacklistEmployeeResDto response = new BlacklistEmployeeResDto();
			response.setBlacklistEmployeeId(be.getId());
			response.setCandidateId(be.getEmployee().getCandidate().getId());
			response.setCandidateName(be.getEmployee().getCandidate().getCandidateProfile().getProfileName());
			
			final List<JobVacancy> jobVacancies = jobDao.getJobByCompany(firstIndex, lastIndex, userDao.getById(principalService.getAuthPrincipal()).getProfile().getCompany().getId());
			JobVacancy jobVacancy = new JobVacancy();
			for(JobVacancy job : jobVacancies) {	
				final AppliedVacancy applied = appliedDao.getByJobVacancyAndCandidate(job.getId(), response.getCandidateId());
				System.err.println(applied.getAppliedProgress().getProgressCode());
				if(AppliedProgressCode.HIRED.progressCode.equals(applied.getAppliedProgress().getProgressCode())){
					jobVacancy = job;
					final VacancyDescription jobDetail = jobDetailDao.getById(jobVacancy.getVacancyDescription().getId());			
					response.setJobTypeName(jobDetail.getJobType().getTypeName());
					response.setVacancyTitle(jobVacancy.getVacancyTitle());
					response.setLevelName(jobVacancy.getExpLevel().getLevelName());
					response.setCreatedAt(DateUtil.dateTimeFormat(be.getCreatedAt()));
					break;
				}
			}
			responses.add(response);
		});
		
		return responses;
	}
}
