package com.lawencon.candidate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.dao.CandidateDao;
import com.lawencon.candidate.dao.CandidateEducationDao;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.education.CandidateEducationCreateReqDto;
import com.lawencon.candidate.dto.education.CandidateEducationResDto;
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

			final HttpStatus status = apiService.writeTo("http://localhost:8081/educations", datas);
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

	/* get educations for candidate */
	public List<CandidateEducationResDto> getEducations() {
		final List<CandidateEducationResDto> responses = new ArrayList<>();

		final Candidate candidate = candidateDao.getById(principalService.getAuthPrincipal());
		final List<CandidateEducation> educations = educationDao.getByCandidateId(candidate.getId());

		for (CandidateEducation education : educations) {
			final CandidateEducationResDto response = new CandidateEducationResDto();
			response.setDegreeId(education.getDegree());
			response.setGpa(education.getGpa());
			response.setEndYear(education.getEndYear().toString());
			response.setId(education.getId());
			response.setInstitutionAddress(education.getInstitutionAddress());
			response.setInstitutionName(education.getInstitutionName());
			response.setMajorId(education.getMajor());
			response.setStartYear(education.getStartYear().toString());
			responses.add(response);
		}

		return responses;
	}

	/* get educations for candidate */
	public List<CandidateEducationResDto> getEducationsList() {
		final Candidate candidate = candidateDao.getById(principalService.getAuthPrincipal());
		final List<CandidateEducation> educations = educationDao.getByCandidateId(candidate.getId());
		
		final String url = "http://localhost:8081/educations/?email=" + candidate.getCandidateEmail();
		final List<CandidateEducationResDto> responses = apiService.getListFrom(url, CandidateEducationResDto.class);
		
		final List<CandidateEducationResDto> responseCandidateEducation = new ObjectMapper().convertValue(responses, new TypeReference<List<CandidateEducationResDto>>() {});
		
//		for (int i = 0; i < responses.size(); i++) {
//			responses.get(i).setId(educations.get(i).getId());
//			responses.get(i).setEndYear(educations.get(i).getEndYear().toString());
//			responses.get(i).setGpa(educations.get(i).getGpa());
//			responses.get(i).setInstitutionAddress(educations.get(i).getInstitutionAddress());
//			responses.get(i).setInstitutionName(educations.get(i).getInstitutionName());
//			responses.get(i).setStartYear(educations.get(i).getStartYear().toString());
//		}

		return responses;
	}

}
