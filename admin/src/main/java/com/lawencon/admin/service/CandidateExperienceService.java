package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.CandidateDao;
import com.lawencon.admin.dao.CandidateExperienceDao;
import com.lawencon.admin.dto.DeleteResDto;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.candidateexperience.CandidateExperienceReqDto;
import com.lawencon.admin.dto.candidateexperience.CandidateExperienceResDto;
import com.lawencon.admin.model.Candidate;
import com.lawencon.admin.model.CandidateExperience;
import com.lawencon.admin.util.DateUtil;
import com.lawencon.base.ConnHandler;

@Service
public class CandidateExperienceService {
	
	@Autowired
	private CandidateDao candidateDao;
	@Autowired
	private CandidateExperienceDao candidateExperienceDao;
	
	/* Insert experiences for candidate */
	public InsertResDto createExperience(List<CandidateExperienceReqDto> data) {
		final InsertResDto response = new InsertResDto();

		ConnHandler.begin();
		try {
			if (data.size() > 0) {
				for (int i = 0; i < data.size(); i++) {
					final CandidateExperience candidateExperience = new CandidateExperience();

					final Candidate candidate = candidateDao.getByEmail(data.get(i).getCandidateEmail());
					candidateExperience.setCandidate(candidate);

					candidateExperience.setFormerInstitution(data.get(i).getFormerInstitution());
					candidateExperience.setFormerPosition(data.get(i).getFormerPosition());
					candidateExperience.setFormerJobdesk(data.get(i).getFormerJobdesc());
					candidateExperience.setFormerLocation(data.get(i).getFormerLocation());
					candidateExperience.setStartDate(DateUtil.dateTimeParse(data.get(i).getStartDate()));
					candidateExperience.setEndDate(DateUtil.dateTimeParse(data.get(i).getEndDate()));
					candidateExperience.setExperienceCode(data.get(i).getExperienceCode());
					candidateExperienceDao.save(candidateExperience);
					data.get(i).setCandidateEmail(candidate.getCandidateEmail());
				}
			}

		} catch (Exception e) {
			ConnHandler.rollback();
		}

		return response;
	}
	
	/* get experiences for admin */
	public List<CandidateExperienceResDto> getExperiencesByCandidateId(String candidateId) {
		final List<CandidateExperienceResDto> responses = new ArrayList<>();
		final Candidate candidate = candidateDao.getById(candidateId);
		final List<CandidateExperience> experiences = candidateExperienceDao.getByCandidateId(candidate.getId());
		
		for (CandidateExperience experience : experiences) {
			final CandidateExperienceResDto response = new CandidateExperienceResDto();
			response.setEndDate(experience.getEndDate().toString());
			response.setFormerInstitution(experience.getFormerInstitution());
			response.setFormerJobdesc(experience.getFormerJobdesk());
			response.setFormerLocation(experience.getFormerLocation());
			response.setFormerPosition(experience.getFormerPosition());
			response.setId(experience.getId());
			response.setStartDate(experience.getStartDate().toString());
			responses.add(response);
		}
		
		return responses;
	}

	/* delete experiences for admin */
	public DeleteResDto deleteExperience(String expCode) {
		ConnHandler.begin();
		
		final DeleteResDto response = new DeleteResDto();
		final CandidateExperience experience = candidateExperienceDao.getByCode(expCode);
		final Boolean result = candidateExperienceDao.deleteById(experience.getId());
		
		if(result) {
			response.setMessage("deleted");
			ConnHandler.commit();
		} else {
			ConnHandler.rollback();
		}
		
		return response;
	}
}
