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
import com.lawencon.admin.dto.hiredemployee.HiredEmployeeResDto;
import com.lawencon.admin.dto.hiredemployee.InsertHiredEmployeeReqDto;
import com.lawencon.admin.model.HiredEmployee;
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
	private UserDao userDao;
	
	/* Move candidate to Hired Stage */
	public InsertResDto hireEmployee(InsertHiredEmployeeReqDto data) {

		ConnHandler.begin();
		final HiredEmployee hired = new HiredEmployee();
		hired.setCandidate(candidateDao.getByIdRef(data.getCandidateId()));
		hired.setCompany(companyDao.getByIdRef(userDao.getById(principalService.getAuthPrincipal()).getProfile().getCompany().getId()));
		final HiredEmployee hiredDb = hiredDao.saveAndFlush(hired);
		ConnHandler.commit();

		final InsertResDto response = new InsertResDto();
		response.setId(hiredDb.getId());
		response.setMessage("Candidate has been moved to Hired!");
		return response;

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
}
