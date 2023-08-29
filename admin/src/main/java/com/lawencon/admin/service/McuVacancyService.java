package com.lawencon.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.AppliedVacancyDao;
import com.lawencon.admin.dao.JobVacancyDao;
import com.lawencon.admin.dao.McuVacancyDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.email.EmailReqDto;
import com.lawencon.admin.dto.email.McuVacancyReqDto;
import com.lawencon.admin.dto.mcuvacancy.InsertMcuVacancyReqDto;
import com.lawencon.admin.dto.mcuvacancy.McuVacancyResDto;
import com.lawencon.admin.model.AppliedVacancy;
import com.lawencon.admin.model.JobVacancy;
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
	private JobVacancyDao jobVacancyDao;
	@Autowired
	private SendMailService mailService;

	public InsertResDto insertMcu(InsertMcuVacancyReqDto data) {

		try {
			ConnHandler.begin();
			final AppliedVacancy appliedVacancy = appliedVacancyDao.getById(data.getAppliedVacancyId()); 
			final McuVacancy mcu = new McuVacancy();
			mcu.setAppliedVacancy(appliedVacancy);
			mcu.setEndDate(DateUtil.dateParse(data.getEndDate()));
			mcu.setStartDate(DateUtil.dateParse(data.getStartDate()));
			final McuVacancy mcuDb = mcuDao.saveAndFlush(mcu);
			ConnHandler.commit();
			
			sendMcu(mcuDb, appliedVacancy.getCandidate().getCandidateEmail());
			
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
	
	public void sendMcu(McuVacancy mcuDb, String email) {
		final McuVacancyReqDto mcuVacancyReqDto = new McuVacancyReqDto();
		
		final AppliedVacancy applied = appliedVacancyDao.getById(mcuDb.getAppliedVacancy().getId());
		final JobVacancy job = jobVacancyDao.getById(applied.getJobVacancy().getId());
		
		mcuVacancyReqDto.setCompanyName(job.getCompany().getCompanyName());
		mcuVacancyReqDto.setCompanyPhoto(job.getCompany().getPhoto().getFileContent());
		mcuVacancyReqDto.setVacancyTitle(job.getVacancyTitle());
		mcuVacancyReqDto.setLevelName(job.getExpLevel().getLevelName());
		mcuVacancyReqDto.setStartDate(mcuDb.getStartDate());
		mcuVacancyReqDto.setEndDate(mcuDb.getEndDate());
		mcuVacancyReqDto.setAddress(job.getCompany().getAddress());
		
		try {				
			final EmailReqDto emailReqDto = new EmailReqDto();
			emailReqDto.setSubject("You has invited to send medical checkup by company");
			emailReqDto.setEmail(email);
			mailService.sendMcu(emailReqDto, mcuVacancyReqDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public McuVacancyResDto getByAppliedId(String appliedVacancyId) {
		final McuVacancy mcuVacancy = mcuDao.getByAppliedVacancyId(appliedVacancyId);
		
		final McuVacancyResDto response = new McuVacancyResDto();
		
		if(mcuVacancy != null) {
			response.setStartDate(DateUtil.dateFormat(mcuVacancy.getStartDate()));
			response.setEndDate(DateUtil.dateFormat(mcuVacancy.getEndDate()));			
			return response;
		} else {
			return null;
		}
		
	}
}
