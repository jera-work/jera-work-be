package com.lawencon.candidate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.dao.CandidateDao;
import com.lawencon.candidate.dao.CandidateEducationDao;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.education.CandidateEducationCreateReqDto;
import com.lawencon.candidate.model.Candidate;
import com.lawencon.candidate.model.CandidateEducation;
import com.lawencon.candidate.util.DateUtil;
import com.lawencon.security.principal.PrincipalServiceImpl;

@Service
public class CandidateEducationService {
	
	@Autowired
	private CandidateDao candidateDao;
	@Autowired
	private PrincipalServiceImpl principalService;
	@Autowired
	private CandidateEducationDao educationDao;
	@Autowired
	private ApiService apiService;
	
	/* Insert educations for candidate */
	public InsertResDto insertCandidateEducations(List<CandidateEducationCreateReqDto> datas) {
		final Candidate candidate = candidateDao.getByIdRef(principalService.getAuthPrincipal());

		try {
			ConnHandler.begin();
			InsertResDto response = null;
			for (CandidateEducationCreateReqDto data : datas) {
				final CandidateEducation education = new CandidateEducation();
				education.setCandidate(candidate);
				education.setDegree(data.getDegreeId());
				education.setEndYear(DateUtil.dateParse(data.getEndYear()));
				education.setGpa(data.getGpa());
				education.setStartYear(DateUtil.dateParse(data.getStartYear()));
				education.setInstitutionAddress(data.getInstitutionAddress());
				education.setInstitutionName(data.getInstitutionName());
				education.setMajor(data.getMajorId());

				data.setCandidateEmail(candidate.getCandidateEmail());
				educationDao.saveAndFlush(education);
			}

			final HttpStatus status = apiService.writeTo("http://localhost:8081/candidates/educations", datas);
			if (status.equals(HttpStatus.CREATED)) {
				response = new InsertResDto();
//				response.setId(educationDb.getId());
				response.setMessage("Educations has been added!");
				ConnHandler.commit();
				return response;
			} else {
				ConnHandler.rollback();
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			ConnHandler.rollback();
			return null;
		}
	}

}
