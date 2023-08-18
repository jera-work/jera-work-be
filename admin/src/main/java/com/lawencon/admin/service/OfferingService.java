package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.AppliedVacancyDao;
import com.lawencon.admin.dao.CandidateDao;
import com.lawencon.admin.dao.JobVacancyDao;
import com.lawencon.admin.dao.OfferingDao;
import com.lawencon.admin.dao.UserDao;
import com.lawencon.admin.dao.VacancyDescriptionDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.email.EmailReqDto;
import com.lawencon.admin.dto.email.OfferingReqDto;
import com.lawencon.admin.dto.offering.InsertOfferingReqDto;
import com.lawencon.admin.model.AppliedVacancy;
import com.lawencon.admin.model.Candidate;
import com.lawencon.admin.model.JobVacancy;
import com.lawencon.admin.model.Offering;
import com.lawencon.admin.model.User;
import com.lawencon.admin.model.VacancyDescription;
import com.lawencon.admin.util.DateUtil;
import com.lawencon.base.ConnHandler;
import com.lawencon.util.JasperUtil;

@Service
public class OfferingService {

	@Autowired
	private OfferingDao offeringDao;
	@Autowired
	private AppliedVacancyDao appliedVacancyDao;
	@Autowired
	private JobVacancyDao jobVacancyDao;
	@Autowired
	private VacancyDescriptionDao vacancyDescriptionDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private CandidateDao candidateDao;
	@Autowired
	private SendMailService mailService;
	@Autowired
	private JasperUtil jasperUtil;

	public InsertResDto insertOffering(InsertOfferingReqDto data) {
		
		ConnHandler.begin();
		final AppliedVacancy appliedVacancy = appliedVacancyDao.getById(data.getAppliedVacancyId()); 
		final Offering offering = new Offering();
		offering.setAppliedVacancy(appliedVacancy);
		offering.setDescription(data.getDescription());
		offering.setEndDate(DateUtil.dateParse(data.getEndDate()));
		offering.setIsApprove(data.getIsApprove());
		offering.setOfferingLocation(data.getOfferingLocation());
		offering.setStartDate(DateUtil.dateParse(data.getStartDate()));
		final Offering offeringDb = offeringDao.saveAndFlush(offering);
		ConnHandler.commit();
		sendOffering(offeringDb, appliedVacancy.getCandidate().getCandidateEmail());
		
//		mailService.sendEmail(appliedVacancy.getCandidate().getCandidateEmail());
		
		final InsertResDto response = new InsertResDto();
		response.setId(offeringDb.getId());
		response.setMessage("Offering has been created!");
		
		return response;
	}
	
	public void sendOffering(Offering offeringDb, String email) {
		final OfferingReqDto offering = new OfferingReqDto();
		
		final AppliedVacancy applied = appliedVacancyDao.getById(offeringDb.getAppliedVacancy().getId());
		final JobVacancy job = jobVacancyDao.getById(applied.getJobVacancy().getId());
		final VacancyDescription jobDesc = vacancyDescriptionDao.getById(job.getVacancyDescription().getId());
		
		final User picHr = userDao.getById(job.getPicHr().getId());
		final Candidate candidate = candidateDao.getById(applied.getCandidate().getId());
		
		offering.setCompanyName(job.getCompany().getCompanyName());
		offering.setCompanyPhoto(job.getCompany().getPhoto().getFileContent());
		offering.setVacancyTitle(job.getVacancyTitle());
		offering.setTypeName(jobDesc.getJobType().getTypeName());
		offering.setLevelName(job.getExpLevel().getLevelName());
		offering.setCandidateName(candidate.getCandidateProfile().getProfileName());
		offering.setPicHrName(picHr.getProfile().getProfileName());
		offering.setSalary(jobDesc.getSalary());	
		offering.setStartDate(offeringDb.getStartDate());
		offering.setEndDate(offeringDb.getEndDate());
		offering.setOfferingLocation(offeringDb.getOfferingLocation());		
		
		final Map<String, Object> parameters = new HashMap<>();
		parameters.put("img", offering.getCompanyPhoto());
		
		final Collection<OfferingReqDto> result = new ArrayList<>();
        result.add(offering);
        
        try {				
        	byte[] dataOut = jasperUtil.responseToByteArray(result, parameters, "jasper-offering");

	        final EmailReqDto emailReqDto = new EmailReqDto();
			emailReqDto.setSubject("Surat Penawaran Kerja");
			emailReqDto.setEmail(email);
			mailService.sendOffering(emailReqDto, offering, dataOut);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
 	}

}
