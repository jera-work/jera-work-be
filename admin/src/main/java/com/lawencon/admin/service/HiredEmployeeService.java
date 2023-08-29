package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.CandidateDao;
import com.lawencon.admin.dao.CompanyDao;
import com.lawencon.admin.dao.HiredEmployeeDao;
import com.lawencon.admin.dao.UserDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.email.EmailReqDto;
import com.lawencon.admin.dto.email.HiredEmployeeReqDto;
import com.lawencon.admin.dto.hiredemployee.HiredEmployeeResDto;
import com.lawencon.admin.dto.hiredemployee.InsertHiredEmployeeReqDto;
import com.lawencon.admin.model.Candidate;
import com.lawencon.admin.model.Company;
import com.lawencon.admin.model.HiredEmployee;
import com.lawencon.admin.model.User;
import com.lawencon.base.ConnHandler;
import com.lawencon.security.principal.PrincipalService;

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
	
	public List<HiredEmployeeResDto> getByCompany(int firstIndex, int lastIndex){
		final List<HiredEmployeeResDto> responses = new ArrayList<>();
		
		hiredDao.getByCompany(firstIndex, lastIndex, userDao.getById(principalService.getAuthPrincipal()).getProfile().getCompany().getId()).forEach(he -> {
			final HiredEmployeeResDto response = new HiredEmployeeResDto();
			response.setHiredEmployeeId(he.getId());
			response.setCandidateName(he.getCandidate().getCandidateProfile().getProfileName());
			response.setCompanyName(he.getCompany().getCompanyName());
			
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
			
			return response;			
		} else {
			return null;
		}
	}
}
