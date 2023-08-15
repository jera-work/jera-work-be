package com.lawencon.candidate.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.dao.CandidateDao;
import com.lawencon.candidate.dao.CustomSkillDao;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.customskill.InsertCustomSkillReqDto;
import com.lawencon.candidate.model.Candidate;
import com.lawencon.candidate.model.CustomCandidateSkill;
import com.lawencon.security.principal.PrincipalService;

public class CustomSkillService {

	@Autowired
	private CustomSkillDao customSkillDao;

	@Autowired
	private CandidateDao candidateDao;

	@Autowired
	private PrincipalService<String> principalService;

	public InsertResDto createSkill(InsertCustomSkillReqDto data) {
		final InsertResDto response = new InsertResDto();

		ConnHandler.begin();
		final CustomCandidateSkill customCandidateSkill = new CustomCandidateSkill();

		final Candidate candidate = candidateDao.getByIdRef(principalService.getAuthPrincipal());
		customCandidateSkill.setCandidate(candidate);
		customCandidateSkill.setSkillName(data.getSkillName());

		final CustomCandidateSkill insertedSkill = customSkillDao.save(customCandidateSkill);

		response.setId(insertedSkill.getId());
		response.setMessage("Skill created successfully");

		ConnHandler.commit();

		return response;
	}
}
