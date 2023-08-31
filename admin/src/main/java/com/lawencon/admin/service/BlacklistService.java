package com.lawencon.admin.service;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.lawencon.admin.dto.blacklistemployee.BlacklistEmployeeCountExpLevel;
import com.lawencon.admin.dto.blacklistemployee.BlacklistEmployeeCountJob;
import com.lawencon.admin.dto.blacklistemployee.BlacklistEmployeeCountJobType;
import com.lawencon.admin.dto.blacklistemployee.BlacklistEmployeeResDto;
import com.lawencon.admin.dto.blacklistemployee.InsertBlacklistEmployeeReqDto;
import com.lawencon.admin.dto.email.EmailReqDto;
import com.lawencon.admin.dto.email.ReportReqDto;
import com.lawencon.admin.dto.jobvacancy.JobVacancyResDto;
import com.lawencon.admin.model.AppliedVacancy;
import com.lawencon.admin.model.BlacklistEmployee;
import com.lawencon.admin.model.Company;
import com.lawencon.admin.model.JobVacancy;
import com.lawencon.admin.model.User;
import com.lawencon.admin.model.VacancyDescription;
import com.lawencon.admin.util.DateUtil;
import com.lawencon.base.ConnHandler;
import com.lawencon.security.principal.PrincipalService;
import com.lawencon.util.JasperUtil;

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
	@Autowired
	private SendMailService sendMailService;
	@Autowired
	private JasperUtil jasperUtil;
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
			
			final List<JobVacancy> jobVacancies = jobDao.getJobByCompany(userDao.getById(principalService.getAuthPrincipal()).getProfile().getCompany().getId());
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
	
	public InsertResDto getReport() {
		final List<BlacklistEmployeeResDto> blacklistEmployeesRes = new ArrayList<>();
		final User userLogin = userDao.getById(principalService.getAuthPrincipal());
		final Company userCompany = companyDao.getById(userLogin.getProfile().getCompany().getId());
		final InsertResDto response = new InsertResDto();
		
		blacklistDao.getByCompany(
				0, 0, 
				userDao.getById(principalService.getAuthPrincipal()).getProfile().getCompany().getId())
					.forEach(be -> {
			final BlacklistEmployeeResDto blacklistEmployeeRes = new BlacklistEmployeeResDto();
			blacklistEmployeeRes.setBlacklistEmployeeId(be.getId());
			blacklistEmployeeRes.setCandidateId(be.getEmployee().getCandidate().getId());
			blacklistEmployeeRes.setCandidateName(be.getEmployee().getCandidate().getCandidateProfile().getProfileName());
			
			final List<JobVacancy> jobVacancies = jobDao.getJobByCompany(userDao.getById(principalService.getAuthPrincipal()).getProfile().getCompany().getId());
			JobVacancy jobVacancy = new JobVacancy();
			for(JobVacancy job : jobVacancies) {	
				final AppliedVacancy applied = appliedDao.getByJobVacancyAndCandidate(job.getId(), blacklistEmployeeRes.getCandidateId());
				System.err.println(applied.getAppliedProgress().getProgressCode());
				if(AppliedProgressCode.HIRED.progressCode.equals(applied.getAppliedProgress().getProgressCode())){
					jobVacancy = job;
					final VacancyDescription jobDetail = jobDetailDao.getById(jobVacancy.getVacancyDescription().getId());			
					blacklistEmployeeRes.setJobTypeName(jobDetail.getJobType().getTypeName());
					blacklistEmployeeRes.setVacancyTitle(jobVacancy.getVacancyTitle());
					blacklistEmployeeRes.setLevelName(jobVacancy.getExpLevel().getLevelName());
					blacklistEmployeeRes.setCreatedAt(DateUtil.dateTimeFormat(be.getCreatedAt()));
					break;
				}
			}
			blacklistEmployeesRes.add(blacklistEmployeeRes);
		});
		
		final List<BlacklistEmployeeCountJobType> blacklistEmployeesCountJobType = new ArrayList<>();
        
        for(int i = 0; i < blacklistEmployeesRes.size(); i++) {
        	final BlacklistEmployeeCountJobType blacklistEmployeeCountJobType = new BlacklistEmployeeCountJobType();
        	blacklistEmployeeCountJobType.setJobTypeName(blacklistEmployeesRes.get(i).getJobTypeName());
        	blacklistEmployeeCountJobType.setJobTypeCount(1);
        	
        	boolean jobTypeExist = false;
        	
        	for(BlacklistEmployeeCountJobType h : blacklistEmployeesCountJobType) {
        		if(h.getJobTypeName().equals(blacklistEmployeesCountJobType.get(i).getJobTypeName())) {
        			jobTypeExist = true;
        		}
        	}
        	
        	if(!jobTypeExist) {
        		blacklistEmployeesCountJobType.add(blacklistEmployeeCountJobType);
        	}else {
        		for(int j = 0; j < blacklistEmployeesCountJobType.size(); j++) {
        			if(blacklistEmployeesCountJobType.get(j).getJobTypeName().equals(blacklistEmployeesRes.get(i).getJobTypeName())) {
        				blacklistEmployeeCountJobType.setJobTypeCount(blacklistEmployeesCountJobType.get(j).getJobTypeCount()+1);
        				blacklistEmployeesCountJobType.add(blacklistEmployeeCountJobType);
        			}
        		}
        	}
        }
        
        final List<BlacklistEmployeeCountExpLevel> blacklistEmployeesCountExpLevel = new ArrayList<>();
        
        for(int i = 0; i < blacklistEmployeesRes.size(); i++) {
        	final BlacklistEmployeeCountExpLevel blacklistEmployeeCountExpLevel = new BlacklistEmployeeCountExpLevel();
        	blacklistEmployeeCountExpLevel.setLevelName(blacklistEmployeesRes.get(i).getLevelName());
        	blacklistEmployeeCountExpLevel.setLevelCount(1);
        	
        	boolean expLevelExist = false;
        	
        	for(BlacklistEmployeeCountExpLevel h : blacklistEmployeesCountExpLevel) {
        		if(h.getLevelName().equals(blacklistEmployeesCountExpLevel.get(i).getLevelName())) {
        			expLevelExist = true;
        		}
        	}
        	
        	if(!expLevelExist) {
        		blacklistEmployeesCountExpLevel.add(blacklistEmployeeCountExpLevel);
        	}else {
        		for(int j = 0; j < blacklistEmployeesCountJobType.size(); j++) {
        			if(blacklistEmployeesCountExpLevel.get(j).getLevelName().equals(blacklistEmployeesRes.get(i).getJobTypeName())) {
        				blacklistEmployeeCountExpLevel.setLevelCount(blacklistEmployeesCountExpLevel.get(j).getLevelCount()+1);
        				blacklistEmployeesCountExpLevel.add(blacklistEmployeeCountExpLevel);
        			}
        		}
        	}
        }
        
        final List<BlacklistEmployeeCountJob> blacklistEmployeesCountJob = new ArrayList<>();
        
        for(int i = 0; i < blacklistEmployeesRes.size(); i++) {
        	final BlacklistEmployeeCountJob blacklistEmployeeCountJob = new BlacklistEmployeeCountJob();
        	blacklistEmployeeCountJob.setJobName(blacklistEmployeesRes.get(i).getVacancyTitle() + " - " + blacklistEmployeesRes.get(i).getLevelName());
        	blacklistEmployeeCountJob.setEmployeeCount(1L);
        	
        	boolean jobExist = false;
        	
        	for(BlacklistEmployeeCountJob h : blacklistEmployeesCountJob) {
        		if(h.getJobName().equals(blacklistEmployeesRes.get(i).getVacancyTitle() + " - " + blacklistEmployeesRes.get(i).getLevelName())) {
        			jobExist = true;
        		}
        	}
        	
        	if(!jobExist) {
        		blacklistEmployeesCountJob.add(blacklistEmployeeCountJob);
        	}else {
        		for(int j = 0; j < blacklistEmployeesCountJob.size(); j++) {
        			if(blacklistEmployeesCountJob.get(j).getJobName().equals(blacklistEmployeesRes.get(i).getVacancyTitle() + " - " + blacklistEmployeesRes.get(i).getLevelName())) {
        				blacklistEmployeeCountJob.setEmployeeCount(blacklistEmployeesCountJob.get(j).getEmployeeCount()+1);
        				blacklistEmployeesCountJob.add(blacklistEmployeeCountJob);
        			}
        		}
        	}
        }
        
        final Collection<List<BlacklistEmployeeResDto>> result = new ArrayList<>();
        result.add(blacklistEmployeesRes);
        
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("blacklistEmployees", blacklistEmployeesRes);
        parameters.put("jobsType", blacklistEmployeesCountJobType);
        parameters.put("expLevels", blacklistEmployeesCountExpLevel);
        parameters.put("jobsTitle", blacklistEmployeesCountJob);
        
        try {				
        	byte[] dataOut = jasperUtil.responseToByteArray(result, parameters, "jasper-blacklist-employee");
                	
	        final EmailReqDto emailReqDto = new EmailReqDto();
			emailReqDto.setSubject("Blacklist Employees Report");
			emailReqDto.setEmail(userLogin.getUserEmail());
			
			final ReportReqDto reportReqDto = new ReportReqDto();
			reportReqDto.setHeader("Blacklist Employee Report");
			reportReqDto.setFullName(userLogin.getProfile().getProfileName());
			reportReqDto.setCompanyName(userCompany.getCompanyName());
			reportReqDto.setCreatedAt(DateUtil.dateTimeFormat(LocalDateTime.now()));
			
			sendMailService.sendBlacklistEmployeeReport(emailReqDto, reportReqDto, dataOut);
			            
			response.setMessage("Report created successfully");
			return response;		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
