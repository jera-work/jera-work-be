package com.lawencon.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.BlacklistEmployeeDao;
import com.lawencon.admin.dao.CandidateDao;
import com.lawencon.admin.dao.CompanyDao;
import com.lawencon.admin.dao.HiredEmployeeDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.blacklistemployee.InsertBlacklistEmployeeReqDto;
import com.lawencon.admin.dto.hiredemployee.InsertHiredEmployeeReqDto;
import com.lawencon.admin.model.BlacklistEmployee;
import com.lawencon.admin.model.HiredEmployee;
import com.lawencon.base.ConnHandler;

@Service
public class BlacklistService {

	@Autowired
	private HiredEmployeeDao hiredDao;
	@Autowired
	private CandidateDao candidateDao;
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private BlacklistEmployeeDao blacklistDao;

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

	/* Move employee to blacklist */
	public InsertResDto blacklistEmployee(InsertBlacklistEmployeeReqDto data) {

		ConnHandler.begin();
		final BlacklistEmployee blacklisted = new BlacklistEmployee();
		blacklisted.setEmployee(hiredDao.getByIdRef(data.getEmployeeId()));
		blacklisted.setCompany(companyDao.getByIdRef(data.getCompanyId()));
		final BlacklistEmployee blacklistedDb = blacklistDao.saveAndFlush(blacklisted);
		ConnHandler.commit();

		final InsertResDto response = new InsertResDto();
		response.setId(blacklistedDb.getId());
		response.setMessage("Candidate has been blacklisted!");
		return response;

	}

}
