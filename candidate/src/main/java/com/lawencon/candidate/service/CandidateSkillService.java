package com.lawencon.candidate.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.dao.CandidateDao;
import com.lawencon.candidate.dao.CandidateSkillDao;
import com.lawencon.candidate.dao.SkillDao;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.candidateskill.CandidateSkillReqDto;
import com.lawencon.candidate.model.Candidate;
import com.lawencon.candidate.model.CandidateSkill;
import com.lawencon.candidate.model.Skill;

public class CandidateSkillService {

	@Autowired
	private CandidateSkillDao candidateSkillDao;
	
	@Autowired
	private CandidateDao candidateDao;
	
	@Autowired
	private SkillDao skillDao;
	
	private InsertResDto createSkill(CandidateSkillReqDto data) {
		final InsertResDto response = new InsertResDto();
		
		ConnHandler.begin();
		try {
			final CandidateSkill candidateSkill = new CandidateSkill();
			final Candidate candidate = candidateDao.getByIdRef(data.getCandidateId());
			candidateSkill.setCandidate(candidate);
			
			final Skill skill = skillDao.getByIdRef(data.getSkillId());
			candidateSkill.setSkill(skill);
			
			final CandidateSkill insertedSkill = candidateSkillDao.save(candidateSkill);
			
			response.setId(insertedSkill.getId());
			response.setMessage("Skill choosen successfully");
			
			ConnHandler.commit();
		} catch (Exception e) {
			ConnHandler.rollback();
		}
		
		return response;
	}
}
