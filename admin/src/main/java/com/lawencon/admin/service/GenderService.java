package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.GenderDao;
import com.lawencon.admin.dto.gender.GenderResDto;
import com.lawencon.admin.model.Gender;

@Service
public class GenderService {

	@Autowired
	private GenderDao genderDao;
	
	public List<GenderResDto> getGenders() {
		final List<Gender> genders = genderDao.getAll();
		final List<GenderResDto> responses = new ArrayList<>();
		
		genders.forEach((gender) -> {
			final GenderResDto response = new GenderResDto();
			response.setGenderId(gender.getId());
			response.setGenderName(gender.getGenderName());
			responses.add(response);
		});
		
		return responses;
	}
	
}
