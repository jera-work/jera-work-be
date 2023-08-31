package com.lawencon.admin.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.CompanyDao;
import com.lawencon.admin.dao.FileDao;
import com.lawencon.admin.dao.UserDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.company.CompanyCreateReqDto;
import com.lawencon.admin.dto.company.CompanyResDto;
import com.lawencon.admin.dto.email.EmailReqDto;
import com.lawencon.admin.dto.email.ReportReqDto;
import com.lawencon.admin.model.Company;
import com.lawencon.admin.model.File;
import com.lawencon.admin.model.User;
import com.lawencon.admin.util.DateUtil;
import com.lawencon.base.ConnHandler;
import com.lawencon.security.principal.PrincipalServiceImpl;
import com.lawencon.util.JasperUtil;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyDao companyDao;
	
	@Autowired
	private FileDao fileDao;
	
	@Autowired
	private JasperUtil jasperUtil;
	
	@Autowired
	private SendMailService sendMailService;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PrincipalServiceImpl principalService;

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
	
	public InsertResDto getReport() {
		final User userLogin = userDao.getById(principalService.getAuthPrincipal());
		final Company userCompany = companyDao.getById(userLogin.getProfile().getCompany().getId());
		final List<Company> companies = companyDao.getAll();
		final List<CompanyResDto> companiesRes = new ArrayList<>();
		final InsertResDto response = new InsertResDto();
		
		companies.forEach(company -> {
			final CompanyResDto companyRes = new CompanyResDto();
			companyRes.setAddress(company.getAddress());
			companyRes.setCompanyCode(company.getCompanyCode());
			companyRes.setCompanyName(company.getCompanyName());
			companyRes.setDescription(company.getDescription());
			companyRes.setId(company.getId());
			companyRes.setPhoneNumber(company.getPhoneNumber());
			companyRes.setPhotoId(company.getPhoto().getId());
			companiesRes.add(companyRes);
		});
		
		final Collection<List<CompanyResDto>> result = new ArrayList<>();
		result.add(companiesRes);
		
		final Map<String, Object> parameters = new HashMap<>();
		parameters.put("companyList", companiesRes);
		
		try {				
        	byte[] dataOut = jasperUtil.responseToByteArray(result, parameters, "jasper-companies");
                	
	        final EmailReqDto emailReqDto = new EmailReqDto();
			emailReqDto.setSubject("Companies Report");
			emailReqDto.setEmail(userLogin.getUserEmail());
			
			final ReportReqDto reportReqDto = new ReportReqDto();
			reportReqDto.setHeader("Company List Report");
			reportReqDto.setFullName(userLogin.getProfile().getProfileName());
			reportReqDto.setCompanyName(userCompany.getCompanyName());
			reportReqDto.setCreatedAt(DateUtil.dateTimeFormat(LocalDateTime.now()));
			
			sendMailService.sendCompaniesReport(emailReqDto, reportReqDto, dataOut);
			            
			response.setMessage("Report created successfully");
			return response;		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

}
