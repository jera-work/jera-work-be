package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.CompanyDao;
import com.lawencon.admin.dao.FileDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.company.CompanyCreateReqDto;
import com.lawencon.admin.dto.company.CompanyResDto;
import com.lawencon.admin.model.Company;
import com.lawencon.admin.model.File;
import com.lawencon.base.ConnHandler;

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
			ConnHandler.commit();
			
			response.setId(companyDb.getId());
			response.setMessage("Company has been created!");
		} catch (Exception e) {
			e.printStackTrace();
			ConnHandler.rollback();
		}
		
		return response;
	}
	
	public List<CompanyResDto> getCompanies() {
		final List<CompanyResDto> responses = new ArrayList<>();
		final List<Company> companies = companyDao.getAll();
		
		companies.forEach(company -> {
			final CompanyResDto response = new CompanyResDto();
			response.setAddress(company.getAddress());
			response.setCompanyCode(company.getCompanyCode());
			response.setCompanyName(company.getCompanyName());
			response.setDescription(company.getDescription());
			response.setId(company.getId());
			response.setPhoneNumber(company.getPhoneNumber());
			response.setPhotoId(company.getPhoto().getId());
			responses.add(response);
		});
		
		return responses;
	}

}
