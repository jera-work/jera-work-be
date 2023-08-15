package com.lawencon.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.AppliedVacancyDao;
import com.lawencon.admin.dao.OfferingDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.offering.InsertOfferingReqDto;
import com.lawencon.admin.model.AppliedVacancy;
import com.lawencon.admin.model.Offering;
import com.lawencon.base.ConnHandler;

@Service
public class OfferingService {

	@Autowired
	private OfferingDao offeringDao;
	@Autowired
	private AppliedVacancyDao appliedVacancyDao;
	@Autowired
	private SendMailService mailService;

	public InsertResDto insertOffering(InsertOfferingReqDto data) {
		
		ConnHandler.begin();
		final AppliedVacancy appliedVacancy = appliedVacancyDao.getById(data.getAppliedVacancyId()); 
		final Offering offering = new Offering();
		offering.setAppliedVacancy(appliedVacancy);
		offering.setDescription(data.getDescription());
		offering.setEndDate(data.getEndDate());
		offering.setIsApprove(data.getIsApprove());
		offering.setOfferingLocation(data.getOfferingLocation());
		offering.setStartDate(data.getStartDate());
		final Offering offeringDb = offeringDao.saveAndFlush(offering);
		ConnHandler.commit();
		
		mailService.sendEmail(appliedVacancy.getCandidate().getCandidateEmail());
		
		final InsertResDto response = new InsertResDto();
		response.setId(offeringDb.getId());
		response.setMessage("Offering has been created!");
		
		return response;
	}

}
