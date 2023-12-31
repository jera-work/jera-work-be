package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.CandidateDao;
import com.lawencon.admin.dao.CandidateSkillDao;
import com.lawencon.admin.dao.SkillDao;
import com.lawencon.admin.dto.DeleteResDto;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.candidateskill.CandidateSkillReqDto;
import com.lawencon.admin.dto.candidateskill.CandidateSkillResDto;
import com.lawencon.admin.model.Candidate;
import com.lawencon.admin.model.CandidateSkill;
import com.lawencon.base.ConnHandler;

@Service
public class CandidateSkillService {

	@Autowired
	private CandidateSkillDao candidateSkillDao;
	@Autowired
	private CandidateDao candidateDao;
	@Autowired
	private SkillDao skillDao;
	@Autowired
	private EmailEncoderService encoderService;
	
	/* insert skills for candidate */
	public InsertResDto createSkill(List<CandidateSkillReqDto> datas) {
		final InsertResDto response = new InsertResDto();
		
		ConnHandler.begin();
		try {
			for (CandidateSkillReqDto data : datas) {
				final Candidate candidate = candidateDao.getByEmail(data.getCandidateEmail());
				final CandidateSkill skill = new CandidateSkill();
				skill.setCandidate(candidate);
				skill.setSkillCode(data.getSkillCode());
				if(skillDao.getById(data.getSkillId()) != null && data.getSkillId() != "") {
					skill.setSkill(skillDao.getById(data.getSkillId()));
				} else {
					skill.setSkillName(data.getSkillId());
				}
				final CandidateSkill skillDb = candidateSkillDao.save(skill);
				response.setId(skillDb.getId());
			}
			response.setMessage("Skill choosen successfully");
			ConnHandler.commit();
			
		} catch (Exception e) {
			ConnHandler.rollback();
		}
		return response;
	}	
	
	/* get skills for candidate */
	public List<CandidateSkillResDto> getSkills(String candidateEmail) {
		final List<CandidateSkillResDto> responses = new ArrayList<>();
		final String email = encoderService.decodeEmail(candidateEmail);
		final Candidate candidate = candidateDao.getByEmail(email);
		final List<CandidateSkill> skills = candidateSkillDao.getByCandidateId(candidate.getId());
		
		for (CandidateSkill skill : skills) {
			final CandidateSkillResDto response = new CandidateSkillResDto();
			if(skill.getSkill() != null) {
				response.setSkillName(skill.getSkill().getSkillName());
			} else {
				response.setSkillName(skill.getSkillName());
			}
			responses.add(response);
		}
		return responses;
	}
	
	/* get skills for admin */
	public List<CandidateSkillResDto> getSkillsByCandidateId(String candidateId) {
		final List<CandidateSkillResDto> responses = new ArrayList<>();
		final Candidate candidate = candidateDao.getById(candidateId);
		final List<CandidateSkill> skills = candidateSkillDao.getByCandidateId(candidate.getId());
		
		for (CandidateSkill skill : skills) {
			final CandidateSkillResDto response = new CandidateSkillResDto();
			response.setSkillId(skill.getId());
			if(skill.getSkill() != null) {
				response.setSkillName(skill.getSkill().getSkillName());
			} else {
				response.setSkillName(skill.getSkillName());
			}
			responses.add(response);
		}
		return responses;
	}
	
	/* delete skills for candidate */
	public DeleteResDto deleteByCode(String skillCode) {
		ConnHandler.begin();
		
		final DeleteResDto response = new DeleteResDto();
		final CandidateSkill skill = candidateSkillDao.getByCode(skillCode);
		final Boolean result = candidateSkillDao.deleteById(skill.getId());
		if(result) {
			response.setMessage("deleted");
			ConnHandler.commit();
		} else {
			ConnHandler.rollback();
		}
		
		return response;
	}
}
