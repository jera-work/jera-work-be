package com.lawencon.candidate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.dao.CandidateDao;
import com.lawencon.candidate.dao.CandidateExperienceDao;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.experience.CandidateExperienceReqDto;
import com.lawencon.candidate.model.Candidate;
import com.lawencon.candidate.model.CandidateExperience;
import com.lawencon.security.principal.PrincipalService;


public class CandidateExperienceService {

	@Autowired
	private PrincipalService<String> principalService;
	
	@Autowired
	private CandidateExperienceDao candidateExperienceDao;
	
	@Autowired
	private CandidateDao candidateDao;
	
	private InsertResDto createExperience(List<CandidateExperienceReqDto> data) {
		final InsertResDto response = new InsertResDto();
		
		ConnHandler.begin();
		try {
			if(data.size() > 0) {
				for(int i = 0; i < data.size(); i++) {
						final CandidateExperience candidateExperience = new CandidateExperience();
						
						final Candidate candidate = candidateDao.getById(principalService.getAuthPrincipal());
						candidateExperience.setCandidate(candidate);
						
						candidateExperience.setFormerInstitution(data.get(i).getFormerInstitution());
						candidateExperience.setFormerPosition(data.get(i).getFormerPosition());
						candidateExperience.setFormerJobdesk(data.get(i).getFormerJobdesk());
						candidateExperience.setFormerLocation(data.get(i).getFormerLocation());
						candidateExperience.setStartDate(data.get(i).getStartDate());
						candidateExperience.setEndDate(data.get(i).getEndDate());
						
						final CandidateExperience inserted = candidateExperienceDao.save(candidateExperience);
				}
			}
			
			
			response.setMessage("Experience(s) successfully created");
			
			ConnHandler.commit();
		} catch (Exception e) {
			ConnHandler.rollback();
		}
		
		return response;
	}
}
