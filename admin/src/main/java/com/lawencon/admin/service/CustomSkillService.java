package com.lawencon.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.CandidateDao;
import com.lawencon.admin.dao.CustomSkillDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.customskill.InsertCustomSkillReqDto;
import com.lawencon.admin.model.Candidate;
import com.lawencon.admin.model.CustomCandidateSkill;
import com.lawencon.base.ConnHandler;

@Service
public class CustomSkillService {

	@Autowired
	private CustomSkillDao customSkillDao;

	@Autowired
	private CandidateDao candidateDao;

	public InsertResDto createSkill(InsertCustomSkillReqDto data) {
		final InsertResDto response = new InsertResDto();

		ConnHandler.begin();
		try {
			final CustomCandidateSkill customCandidateSkill = new CustomCandidateSkill();

			final Candidate candidate = candidateDao.getByIdRef(data.getCandidateId());
			customCandidateSkill.setCandidate(candidate);
			customCandidateSkill.setSkillName(data.getSkillName());
			
			final CustomCandidateSkill insertedSkill = customSkillDao.save(customCandidateSkill);

			response.setId(insertedSkill.getId());
			response.setMessage("Skill created successfully");

			ConnHandler.commit();
		} catch (Exception e) {
			ConnHandler.rollback();
		}

		return response;
	}
}
