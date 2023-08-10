package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.CompanyDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.company.CompanyCreateReqDto;
import com.lawencon.admin.dto.company.CompanyResDto;
import com.lawencon.admin.model.Company;
import com.lawencon.admin.model.File;

@Service
public class CompanyService {

	@Autowired
	private CompanyDao companyDao;

	public InsertResDto createCompany(CompanyCreateReqDto data) {
		
		final Company company = new Company();
		company.setCompanyName(data.getCompanyName());
		company.setCompanyCode(data.getCompanyCode());
		company.setAddress(data.getAddress());
		company.setDescription(data.getDescription());
		company.setPhoneNumber(data.getPhoneNumber());
		
		final File logo = new File();
		logo.setFileContent(data.getFileContent());
		logo.setFileExt(data.getFileExt());
		
		company.setPhoto(logo);
		final Company companyDb = companyDao.save(company);
		
		final InsertResDto response = new InsertResDto();
		response.setId(companyDb.getId());
		response.setMessage("Company : " + companyDb.getCompanyName() + " has been created!");
		
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
