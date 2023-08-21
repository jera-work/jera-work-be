package com.lawencon.candidate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.dao.CandidateDao;
import com.lawencon.candidate.dao.CandidateExperienceDao;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.experience.CandidateExperienceReqDto;
import com.lawencon.candidate.model.Candidate;
import com.lawencon.candidate.model.CandidateExperience;
import com.lawencon.candidate.util.DateUtil;
import com.lawencon.security.principal.PrincipalServiceImpl;

@Service
public class CandidateExperienceService {
	
	@Autowired
	private CandidateDao candidateDao;
	@Autowired
	private PrincipalServiceImpl principalService;
	@Autowired
	private CandidateExperienceDao candidateExperienceDao;
	@Autowired
	private ApiService apiService;
	
	/* Insert experiences for candidate */
	public InsertResDto createExperience(List<CandidateExperienceReqDto> data) {
		final InsertResDto response = new InsertResDto();

		ConnHandler.begin();
		try {
			if (data.size() > 0) {
				for (int i = 0; i < data.size(); i++) {
					final CandidateExperience candidateExperience = new CandidateExperience();

					final Candidate candidate = candidateDao.getById(principalService.getAuthPrincipal());
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

			final HttpStatus status = apiService.writeTo("http://localhost:8081/candidates/experiences", data);
			if (status.equals(HttpStatus.CREATED)) {
				response.setMessage("Experience(s) successfully created");
				ConnHandler.commit();
			} else {
				ConnHandler.rollback();
			}

		} catch (Exception e) {
			ConnHandler.rollback();
		}

		return response;
	}

}
