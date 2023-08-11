package com.lawencon.candidate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.dao.CompanyDao;
import com.lawencon.candidate.dao.FileDao;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.company.CompanyCreateReqDto;
import com.lawencon.candidate.model.Company;
import com.lawencon.candidate.model.File;

@Service
public class CompanyService {

	@Autowired
	private CompanyDao companyDao;
	
	@Autowired
	private FileDao fileDao;

	public InsertResDto createCompany(CompanyCreateReqDto data) {

		final InsertResDto response = new InsertResDto();
		final Company company = new Company();
		
		try {
			ConnHandler.begin();
			company.setCompanyName(data.getCompanyName());
			company.setCompanyCode(data.getCompanyCode());
			company.setAddress(data.getAddress());
			company.setDescription(data.getDescription());
			company.setPhoneNumber(data.getPhoneNumber());
			
			final File logo = new File();
			logo.setFileContent(data.getFileContent());
			logo.setFileExt(data.getFileExt());
			
			fileDao.saveAndFlush(logo);
			
			company.setPhoto(logo);
			final Company companyDb = companyDao.save(company);
			
			response.setId(companyDb.getId());
			response.setMessage("Company : " + companyDb.getCompanyName() + " has been created!");
			
			ConnHandler.commit();
		} catch (Exception e) {
			ConnHandler.rollback();
		}
		
		return response;
	}

}
