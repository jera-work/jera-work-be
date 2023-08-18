package com.lawencon.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.AppliedVacancyDao;
import com.lawencon.admin.dao.McuVacancyDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.mcuvacancy.InsertMcuVacancyReqDto;
import com.lawencon.admin.dto.mcuvacancy.McuVacancyResDto;
import com.lawencon.admin.model.AppliedVacancy;
import com.lawencon.admin.model.McuVacancy;
import com.lawencon.admin.util.DateUtil;
import com.lawencon.base.ConnHandler;

@Service
public class McuVacancyService {

	@Autowired
	private McuVacancyDao mcuDao;
	@Autowired
	private AppliedVacancyDao appliedVacancyDao;
	@Autowired
	private SendMailService mailService;

	public InsertResDto insertMcu(InsertMcuVacancyReqDto data) {

		try {
			ConnHandler.begin();
			final AppliedVacancy appliedVacancy = appliedVacancyDao.getById(data.getAppliedVacancy()); 
			final McuVacancy mcu = new McuVacancy();
			mcu.setAppliedVacancy(appliedVacancy);
			mcu.setEndDate(data.getEndDate());
			mcu.setStartDate(data.getStartDate());
			final McuVacancy mcuDb = mcuDao.saveAndFlush(mcu);
			ConnHandler.commit();

//			mailService.sendEmail(appliedVacancy.getCandidate().getCandidateEmail());
			
			final InsertResDto response = new InsertResDto();
			response.setId(mcuDb.getId());
			response.setMessage("MCU has been created!");

			return response;
		} catch (Exception e) {
			e.printStackTrace();
			ConnHandler.rollback();
			return null;
		}
	}

	public McuVacancyResDto getByAppliedId(String appliedVacancyId) {
		final McuVacancy mcuVacancy = mcuDao.getByAppliedVacancyId(appliedVacancyId);
		
		final McuVacancyResDto response = new McuVacancyResDto();
		response.setStartDate(DateUtil.dateFormat(mcuVacancy.getStartDate()));
		response.setEndDate(DateUtil.dateFormat(mcuVacancy.getEndDate()));
		
		return response;
	}
}
