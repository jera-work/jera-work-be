//package com.lawencon.admin.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.lawencon.admin.dao.JobVacancyDao;
//import com.lawencon.admin.dao.UserDao;
//import com.lawencon.admin.dto.InsertResDto;
//import com.lawencon.admin.dto.jobvacancy.InsertJobVacancyReqDto;
//import com.lawencon.admin.model.JobVacancy;
//import com.lawencon.admin.model.User;
//
//public class JobVacancyService {
//
//	@Autowired
//	private UserDao userDao;
//	@Autowired
//	private JobVacancyDao jobVacancyDao;
//	
//	public InsertResDto create(InsertJobVacancyReqDto data) {
//		
//		final JobVacancy jobVacancy = new JobVacancy();
//		jobVacancy.setVacancyCode(data.getVacancyCode());
//		jobVacancy.setVacancyTitle(data.getVacancyTitle());
//		
//		final User picHr = userDao.getByIdRef(data.getPicHrId());
//		jobVacancy.setPicHr(picHr);
//		final InsertResDto response = new InsertResDto();
//		return response;
//	}
//	
//}
