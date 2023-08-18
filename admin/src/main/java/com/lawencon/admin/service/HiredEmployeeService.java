package com.lawencon.admin.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.CandidateDao;
import com.lawencon.admin.dao.CompanyDao;
import com.lawencon.admin.dao.HiredEmployeeDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.hiredemployee.InsertHiredEmployeeReqDto;
import com.lawencon.admin.model.HiredEmployee;
import com.lawencon.base.ConnHandler;

@Service
public class HiredEmployeeService {
	
	@Autowired
	private CandidateDao candidateDao;
	@Autowired
	private HiredEmployeeDao hiredDao;
	@Autowired
	private CompanyDao companyDao;
	
	/* Move candidate to Hired Stage */
	public InsertResDto hireEmployee(InsertHiredEmployeeReqDto data) {

		ConnHandler.begin();
		final HiredEmployee hired = new HiredEmployee();
		hired.setCandidate(candidateDao.getByIdRef(data.getCandidateId()));
		hired.setCompany(companyDao.getByIdRef(data.getCompanyId()));
		final HiredEmployee hiredDb = hiredDao.saveAndFlush(hired);
		ConnHandler.commit();

		final InsertResDto response = new InsertResDto();
		response.setId(hiredDb.getId());
		response.setMessage("Candidate has been moved to Hired!");
		return response;

	}

}
