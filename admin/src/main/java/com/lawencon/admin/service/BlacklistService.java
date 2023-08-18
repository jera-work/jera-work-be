package com.lawencon.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.BlacklistEmployeeDao;
import com.lawencon.admin.dao.CompanyDao;
import com.lawencon.admin.dao.HiredEmployeeDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.blacklistemployee.InsertBlacklistEmployeeReqDto;
import com.lawencon.admin.model.BlacklistEmployee;
import com.lawencon.base.ConnHandler;

@Service
public class BlacklistService {

	@Autowired
	private HiredEmployeeDao hiredDao;
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private BlacklistEmployeeDao blacklistDao;

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
