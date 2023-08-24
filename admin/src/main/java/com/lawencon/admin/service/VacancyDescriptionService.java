package com.lawencon.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.VacancyDescriptionDao;
import com.lawencon.admin.dto.vacancydescription.VacancyDescriptionResDto;
import com.lawencon.admin.model.VacancyDescription;

@Service
public class VacancyDescriptionService {

	@Autowired
	private VacancyDescriptionDao vacancyDescriptionDao;
	
	public VacancyDescriptionResDto getDetail(String descId) {
		final VacancyDescription vd = vacancyDescriptionDao.getById(descId);
		
		final VacancyDescriptionResDto response = new VacancyDescriptionResDto();
		response.setAddress(vd.getAddress());
		response.setAgeVacancyName(vd.getAgeVacancy().getAgeName());
		response.setCityName(vd.getCity().getCityName());
		response.setDegreeName(vd.getDegree().getDegreeName());
		response.setDescription(vd.getDescription());
		response.setGenderName(vd.getGender().getGenderName());
		response.setJobTypeName(vd.getJobType().getTypeName());
		response.setSalary(vd.getSalary());
		
		return response;
	}
}
