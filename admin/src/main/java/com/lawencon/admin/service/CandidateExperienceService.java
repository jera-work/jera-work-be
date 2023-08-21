package com.lawencon.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.CandidateDao;
import com.lawencon.admin.dao.CandidateExperienceDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.experience.CandidateExperienceReqDto;
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
					candidateExperience.setFormerJobdesk(data.get(i).getFormerJobdesk());
					candidateExperience.setFormerLocation(data.get(i).getFormerLocation());
					candidateExperience.setStartDate(DateUtil.dateParse(data.get(i).getStartDate()));
					candidateExperience.setEndDate(DateUtil.dateParse(data.get(i).getEndDate()));

					candidateExperienceDao.save(candidateExperience);
					data.get(i).setCandidateEmail(candidate.getCandidateEmail());
				}
			}

		} catch (Exception e) {
			ConnHandler.rollback();
		}

		return response;
	}

}
