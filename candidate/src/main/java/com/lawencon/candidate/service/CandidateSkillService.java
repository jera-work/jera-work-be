package com.lawencon.candidate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.dao.CandidateDao;
import com.lawencon.candidate.dao.CandidateSkillDao;
import com.lawencon.candidate.dto.DeleteResDto;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.candidateskill.CandidateSkillReqDto;
import com.lawencon.candidate.dto.candidateskill.CandidateSkillResDto;
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
	@Autowired
	private EmailEncoderService encoderService;
	
	/* insert skills for candidate */
	public InsertResDto createSkill(List<CandidateSkillReqDto> datas) {
		final InsertResDto response = new InsertResDto();
		
		ConnHandler.begin();
		try {
			for (CandidateSkillReqDto data : datas) {
				final Candidate candidate = candidateDao.getById(principalService.getAuthPrincipal());
				final CandidateSkill skill = new CandidateSkill();
				skill.setCandidate(candidate);
				if(data.getSkillId() != null && data.getSkillId() != "") {
					skill.setSkill(data.getSkillId());
					skill.setSkillName(data.getSkillId());
				}
				final CandidateSkill skillDb = candidateSkillDao.save(skill);
				
				data.setCandidateEmail(candidate.getCandidateEmail());
				response.setId(skillDb.getId());
			}
			response.setMessage("Skill choosen successfully");
			
			apiService.writeTo("http://localhost:8081/skills", datas);
			ConnHandler.commit();
			
		} catch (Exception e) {
			ConnHandler.rollback();
		}
		return response;
	}
	
	/* get skills for candidate */
	public List<CandidateSkillResDto> getSkills() {
		final Candidate candidate = candidateDao.getById(principalService.getAuthPrincipal());
		final List<CandidateSkill> skills = candidateSkillDao.getByCandidateId(candidate.getId());
		final String encodedEmail = encoderService.encodeEmail(candidate.getCandidateEmail());
		
		final String url = "http://localhost:8081/skills/?email=" + encodedEmail;
		final List<CandidateSkillResDto> responseFromAdmin = apiService.getListFrom(url, CandidateSkillResDto.class);
		final List<CandidateSkillResDto> responses = new ObjectMapper().convertValue(responseFromAdmin, new TypeReference<List<CandidateSkillResDto>>() {});
		
		for (int i = 0; i < responses.size(); i++) {
			responses.get(i).setSkillId(skills.get(i).getId());
		}
		
		return responses;
	}
	
	/* delete skills for candidate */
	public DeleteResDto deleteSkill(String skillId) {
		ConnHandler.begin();
		
		final CandidateSkill cs = candidateSkillDao.getById(skillId);
		final Candidate cdt = candidateDao.getById(principalService.getAuthPrincipal());
		
		final DeleteResDto response = new DeleteResDto();
		final Boolean skill = candidateSkillDao.deleteById(skillId);
		
		if(skill) {
			response.setMessage("Skill has been deleted!");
			ConnHandler.commit();
		} else {
			ConnHandler.rollback();
		}
		
		return response;
	}
	
}
