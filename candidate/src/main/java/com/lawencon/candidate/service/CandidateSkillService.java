package com.lawencon.candidate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.dao.CandidateDao;
import com.lawencon.candidate.dao.CandidateSkillDao;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.candidateskill.CandidateSkillReqDto;
import com.lawencon.candidate.model.Candidate;
import com.lawencon.candidate.model.CandidateSkill;
import com.lawencon.security.principal.PrincipalServiceImpl;

@Service
public class CandidateSkillService {

	@Autowired
	private CandidateSkillDao candidateSkillDao;
	@Autowired
	private CandidateDao candidateDao;
	@Autowired
	private PrincipalServiceImpl principalService;
	@Autowired
	private ApiService apiService;
	
	/* insert skills for candidate */
	public InsertResDto createSkill(List<CandidateSkillReqDto> datas) {
		final InsertResDto response = new InsertResDto();
		
		ConnHandler.begin();
		try {
			for (CandidateSkillReqDto data : datas) {
				final Candidate candidate = candidateDao.getById(principalService.getAuthPrincipal());
				final CandidateSkill skill = new CandidateSkill();
				skill.setCandidate(candidate);
				skill.setSkill(data.getSkillId());
				final CandidateSkill skillDb = candidateSkillDao.save(skill);
				
				data.setCandidateEmail(candidate.getCandidateEmail());
				response.setId(skillDb.getId());
			}
			response.setMessage("Skill choosen successfully");
			
			apiService.writeTo("http://localhost:8081/candidates/skills", datas);
			ConnHandler.commit();
			
		} catch (Exception e) {
			ConnHandler.rollback();
		}
		return response;
	}
}
