package com.lawencon.admin.service;

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
import com.lawencon.admin.dao.CandidateDao;
import com.lawencon.admin.dao.CompanyDao;
import com.lawencon.admin.dao.HiredEmployeeDao;
import com.lawencon.admin.dao.JobVacancyDao;
import com.lawencon.admin.dao.UserDao;
import com.lawencon.admin.dao.VacancyDescriptionDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.email.EmailReqDto;
import com.lawencon.admin.dto.email.HiredEmployeeReqDto;
import com.lawencon.admin.dto.email.ReportReqDto;
import com.lawencon.admin.dto.hiredemployee.HiredEmployeeResDto;
import com.lawencon.admin.dto.hiredemployee.InsertHiredEmployeeReqDto;
import com.lawencon.admin.model.AppliedVacancy;
import com.lawencon.admin.model.Candidate;
import com.lawencon.admin.model.Company;
import com.lawencon.admin.model.HiredEmployee;
import com.lawencon.admin.model.JobVacancy;
import com.lawencon.admin.model.User;
import com.lawencon.admin.model.VacancyDescription;
import com.lawencon.admin.util.DateUtil;
import com.lawencon.base.ConnHandler;
import com.lawencon.admin.dto.hiredemployee.HiredEmployeeCountExpLevel;
import com.lawencon.admin.dto.hiredemployee.HiredEmployeeCountJob;
import com.lawencon.admin.dto.hiredemployee.HiredEmployeeCountJobType;
import com.lawencon.security.principal.PrincipalService;
import com.lawencon.util.JasperUtil;

@Service
public class HiredEmployeeService {
	
	@Autowired
	private PrincipalService<String> principalService;
	
	@Autowired
	private CandidateDao candidateDao;
	
	@Autowired
	private CompanyDao companyDao;
	
	@Autowired
	private HiredEmployeeDao hiredDao;
	
	@Autowired
	private SendMailService mailService;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private JobVacancyDao jobDao;
	
	@Autowired
	private AppliedVacancyDao appliedDao;
	
	@Autowired
	private VacancyDescriptionDao jobDetailDao;
	
	@Autowired
	private JasperUtil jasperUtil;
	
	@Autowired
	private SendMailService sendMailService;
	
	/* Move candidate to Hired Stage */
	public InsertResDto hireEmployee(InsertHiredEmployeeReqDto data) {

		ConnHandler.begin();
		final HiredEmployee hired = new HiredEmployee();
		hired.setCandidate(candidateDao.getByIdRef(data.getCandidateId()));
		hired.setCompany(companyDao.getByIdRef(userDao.getById(principalService.getAuthPrincipal()).getProfile().getCompany().getId()));
		final HiredEmployee hiredDb = hiredDao.saveAndFlush(hired);
		ConnHandler.commit();
		
		final Candidate candidate = candidateDao.getById(data.getCandidateId());
		sendHired(hiredDb, candidate.getCandidateEmail());

		final InsertResDto response = new InsertResDto();
		response.setId(hiredDb.getId());
		response.setMessage("Candidate has been moved to Hired!");
		return response;

	}
	
	public void sendHired(HiredEmployee hiredDb, String email) {
		final HiredEmployeeReqDto hiredEmployeeReqDto = new HiredEmployeeReqDto();
		
		final Company company = companyDao.getById(hiredDb.getCompany().getId());
		
		hiredEmployeeReqDto.setCompanyName(company.getCompanyName());
		hiredEmployeeReqDto.setCompanyPhoto(company.getPhoto().getFileContent());
		
		try {				
			final EmailReqDto emailReqDto = new EmailReqDto();
			emailReqDto.setSubject("You has hired by company");
			emailReqDto.setEmail(email);
			mailService.sendHired(emailReqDto, hiredEmployeeReqDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<HiredEmployeeResDto> getByCompany(){
		final List<HiredEmployeeResDto> responses = new ArrayList<>();
		final User user = userDao.getById(principalService.getAuthPrincipal());
		
		hiredDao.getByCompany(user.getProfile().getCompany().getId()).forEach(he -> {
			final HiredEmployeeResDto response = new HiredEmployeeResDto();
			response.setHiredEmployeeId(he.getId());
			response.setCandidateName(he.getCandidate().getCandidateProfile().getProfileName());
			response.setCompanyName(he.getCompany().getCompanyName());
			
			final List<JobVacancy> jobVacancies = jobDao.getJobByCompany(user.getProfile().getCompany().getId());
			JobVacancy jobVacancy = new JobVacancy();
			for(JobVacancy job : jobVacancies) {
				final AppliedVacancy applied = (appliedDao.getByJobVacancyAndCandidate(job.getId(), he.getCandidate().getId()) != null) ? appliedDao.getByJobVacancyAndCandidate(job.getId(), he.getCandidate().getId()) : null;
				if(applied != null && AppliedProgressCode.HIRED.progressCode.equals(applied.getAppliedProgress().getProgressCode())){
					jobVacancy = job;
					final VacancyDescription jobDetail = jobDetailDao.getById(jobVacancy.getVacancyDescription().getId());			
					response.setJobTypeName(jobDetail.getJobType().getTypeName());
					response.setVacancyTitle(jobVacancy.getVacancyTitle());
					response.setLevelName(jobVacancy.getExpLevel().getLevelName());
					response.setCreatedAt(DateUtil.dateTimeFormatIso(he.getCreatedAt()));
					response.setAppliedId(applied.getId());
					break;
				}
			}
			responses.add(response);
		});
		
		return responses;
	}
	
	public HiredEmployeeResDto getByCandidateId(String candidateId) {
		final User user = userDao.getById(principalService.getAuthPrincipal());
		final HiredEmployee employee = hiredDao.getByCandidate(user.getProfile().getCompany().getId(), candidateId);
		final Candidate cdt = candidateDao.getById(candidateId);
		
		if(employee != null) {
			final HiredEmployeeResDto response = new HiredEmployeeResDto();
			response.setCandidateName(cdt.getCandidateProfile().getProfileName());
			response.setCompanyName(employee.getCompany().getCompanyName());
			response.setHiredEmployeeId(employee.getId());
			response.setCreatedAt(DateUtil.dateTimeFormat(employee.getCreatedAt()));
			return response;
		} else {
			return null;
		}
	}
	
	public InsertResDto getReport() {
		final List<HiredEmployeeResDto> hiredEmployeesRes = new ArrayList<>();
		final User userLogin = userDao.getById(principalService.getAuthPrincipal());
		final Company userCompany = companyDao.getById(userLogin.getProfile().getCompany().getId());
		final InsertResDto response = new InsertResDto();
		
		hiredDao.getByCompany(userDao.getById(principalService.getAuthPrincipal()).getProfile().getCompany().getId()).forEach(he -> {
			final HiredEmployeeResDto hiredEmployeeRes = new HiredEmployeeResDto();
			hiredEmployeeRes.setHiredEmployeeId(he.getId());
			hiredEmployeeRes.setCandidateName(he.getCandidate().getCandidateProfile().getProfileName());
			
			final List<JobVacancy> jobVacancies = jobDao.getJobByCompany(userDao.getById(principalService.getAuthPrincipal()).getProfile().getCompany().getId());
			JobVacancy jobVacancy = new JobVacancy();
			for(JobVacancy job : jobVacancies) {	
				final AppliedVacancy applied = appliedDao.getByJobVacancyAndCandidate(job.getId(), he.getCandidate().getId());
				if(AppliedProgressCode.HIRED.progressCode.equals(applied.getAppliedProgress().getProgressCode())){
					jobVacancy = job;
					final VacancyDescription jobDetail = jobDetailDao.getById(jobVacancy.getVacancyDescription().getId());			
					hiredEmployeeRes.setJobTypeName(jobDetail.getJobType().getTypeName());
					hiredEmployeeRes.setVacancyTitle(jobVacancy.getVacancyTitle());
					hiredEmployeeRes.setLevelName(jobVacancy.getExpLevel().getLevelName());
					hiredEmployeeRes.setCreatedAt(DateUtil.dateTimeFormat(he.getCreatedAt()));
					break;
				}
			}
			hiredEmployeesRes.add(hiredEmployeeRes);
		});
		
		final List<HiredEmployeeCountJobType> hiredEmployeesCountJobType = new ArrayList<>();
        
        for(int i = 0; i < hiredEmployeesRes.size(); i++) {
        	final HiredEmployeeCountJobType hiredEmployeeCountJobType = new HiredEmployeeCountJobType();
        	hiredEmployeeCountJobType.setJobTypeName(hiredEmployeesRes.get(i).getJobTypeName());
        	hiredEmployeeCountJobType.setJobTypeCount(1);
        	
        	boolean jobTypeExist = false;
        	
        	for(HiredEmployeeCountJobType h : hiredEmployeesCountJobType) {
        		if(h.getJobTypeName().equals(hiredEmployeesCountJobType.get(i).getJobTypeName())) {
        			jobTypeExist = true;
        		}
        	}
        	
        	if(!jobTypeExist) {
        		hiredEmployeesCountJobType.add(hiredEmployeeCountJobType);
        	}else {
        		for(int j = 0; j < hiredEmployeesCountJobType.size(); j++) {
        			if(hiredEmployeesCountJobType.get(j).getJobTypeName().equals(hiredEmployeesRes.get(i).getJobTypeName())) {
        				hiredEmployeeCountJobType.setJobTypeCount(hiredEmployeesCountJobType.get(j).getJobTypeCount()+1);
        				hiredEmployeesCountJobType.add(hiredEmployeeCountJobType);
        			}
        		}
        	}
        }
        
        final List<HiredEmployeeCountExpLevel> hiredEmployeesCountExpLevel = new ArrayList<>();
        
        for(int i = 0; i < hiredEmployeesRes.size(); i++) {
        	final HiredEmployeeCountExpLevel hiredEmployeeCountExpLevel = new HiredEmployeeCountExpLevel();
        	hiredEmployeeCountExpLevel.setLevelName(hiredEmployeesRes.get(i).getLevelName());
        	hiredEmployeeCountExpLevel.setLevelCount(1);
        	
        	boolean expLevelExist = false;
        	
        	for(HiredEmployeeCountExpLevel h : hiredEmployeesCountExpLevel) {
        		if(h.getLevelName().equals(hiredEmployeesCountExpLevel.get(i).getLevelName())) {
        			expLevelExist = true;
        		}
        	}
        	
        	if(!expLevelExist) {
        		hiredEmployeesCountExpLevel.add(hiredEmployeeCountExpLevel);
        	}else {
        		for(int j = 0; j < hiredEmployeesCountJobType.size(); j++) {
        			if(hiredEmployeesCountExpLevel.get(j).getLevelName().equals(hiredEmployeesRes.get(i).getJobTypeName())) {
        				hiredEmployeeCountExpLevel.setLevelCount(hiredEmployeesCountExpLevel.get(j).getLevelCount()+1);
        				hiredEmployeesCountExpLevel.add(hiredEmployeeCountExpLevel);
        			}
        		}
        	}
        }
        
        final List<HiredEmployeeCountJob> hiredEmployeesCountJob = new ArrayList<>();
        
        for(int i = 0; i < hiredEmployeesRes.size(); i++) {
        	final HiredEmployeeCountJob hiredEmployeeCountJob = new HiredEmployeeCountJob();
        	hiredEmployeeCountJob.setJobName(hiredEmployeesRes.get(i).getVacancyTitle() + " - " + hiredEmployeesRes.get(i).getLevelName());
        	hiredEmployeeCountJob.setEmployeeCount(1L);
        	
        	boolean jobExist = false;
        	
        	for(HiredEmployeeCountJob h : hiredEmployeesCountJob) {
        		if(h.getJobName().equals(hiredEmployeesRes.get(i).getVacancyTitle() + " - " + hiredEmployeesRes.get(i).getLevelName())) {
        			jobExist = true;
        		}
        	}
        	
        	if(!jobExist) {
        		hiredEmployeesCountJob.add(hiredEmployeeCountJob);
        	}else {
        		for(int j = 0; j < hiredEmployeesCountJob.size(); j++) {
        			if(hiredEmployeesCountJob.get(j).getJobName().equals(hiredEmployeesRes.get(i).getVacancyTitle() + " - " + hiredEmployeesRes.get(i).getLevelName())) {
        				hiredEmployeeCountJob.setEmployeeCount(hiredEmployeesCountJob.get(j).getEmployeeCount()+1);
        				hiredEmployeesCountJob.add(hiredEmployeeCountJob);
        			}
        		}
        	}
        }
        
        final Collection<List<HiredEmployeeResDto>> result = new ArrayList<>();
        result.add(hiredEmployeesRes);
        
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("hiredEmployees", hiredEmployeesRes);
        parameters.put("jobsType", hiredEmployeesCountJobType);
        parameters.put("expLevels", hiredEmployeesCountExpLevel);
        parameters.put("jobsTitle", hiredEmployeesCountJob);
        
        try {				
        	byte[] dataOut = jasperUtil.responseToByteArray(result, parameters, "jasper-hired-employee");
                	
	        final EmailReqDto emailReqDto = new EmailReqDto();
			emailReqDto.setSubject("Hired Employees Report");
			emailReqDto.setEmail(userLogin.getUserEmail());
			
			final ReportReqDto reportReqDto = new ReportReqDto();
			reportReqDto.setHeader("Hired Employee List Report");
			reportReqDto.setFullName(userLogin.getProfile().getProfileName());
			reportReqDto.setCompanyName(userCompany.getCompanyName());
			reportReqDto.setCreatedAt(DateUtil.dateTimeFormat(LocalDateTime.now()));
			
			sendMailService.sendHiredEmployeeReport(emailReqDto, reportReqDto, dataOut);
			            
			response.setMessage("Report created successfully");
			return response;		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
