package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.SkillDao;
import com.lawencon.admin.dto.skill.SkillResDto;

@Service
public class SkillService {
	@Autowired
	private SkillDao skillDao;
	
	public List<SkillResDto> getAll() {
		final List<SkillResDto> responses = new ArrayList<>();
		
		skillDao.getAll().forEach(av -> {
			final SkillResDto response = new SkillResDto();
			//
			
			responses.add(response);
		});
		
		return responses;
	}
}
