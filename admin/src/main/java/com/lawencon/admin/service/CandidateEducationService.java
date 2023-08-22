package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.CandidateDao;
import com.lawencon.admin.dao.CandidateEducationDao;
import com.lawencon.admin.dao.DegreeDao;
import com.lawencon.admin.dao.MajorDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.education.CandidateEducationCreateReqDto;
import com.lawencon.admin.dto.education.CandidateEducationResDto;
import com.lawencon.admin.model.Candidate;
import com.lawencon.admin.model.CandidateEducation;
import com.lawencon.admin.util.DateUtil;
import com.lawencon.base.ConnHandler;

@Service
public class CandidateEducationService {
	
	@Autowired
	private CandidateDao candidateDao;
	@Autowired
	private CandidateEducationDao educationDao;
	@Autowired
	private DegreeDao degreeDao;
	@Autowired
	private MajorDao majorDao;
	
	/* Insert educations for candidate */
	public InsertResDto insertCandidateEducations(List<CandidateEducationCreateReqDto> datas) {

		try {
			ConnHandler.begin();
			InsertResDto response = null;
			for (CandidateEducationCreateReqDto data : datas) {
				final Candidate candidate = candidateDao.getByEmail(data.getCandidateEmail());
				final CandidateEducation education = new CandidateEducation();
				education.setCandidate(candidate);
				education.setDegree(degreeDao.getByIdRef(data.getDegreeId()));
				education.setEndYear(DateUtil.dateParse(data.getEndYear()));
				education.setGpa(data.getGpa());
				education.setStartYear(DateUtil.dateParse(data.getStartYear()));
				education.setInstitutionAddress(data.getInstitutionAddress());
				education.setInstitutionName(data.getInstitutionName());
				education.setMajor(majorDao.getByIdRef(data.getMajorId()));

				final CandidateEducation educationDb = educationDao.save(education);

				response = new InsertResDto();
				response.setId(educationDb.getId());
				response.setMessage("Educations has been added!");
			}
			ConnHandler.commit();
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			ConnHandler.rollback();
			return null;
		}
	}
	
	/* get educations for candidate */ 
	public List<CandidateEducationResDto> getEducations(String candidateEmail) {
		final List<CandidateEducationResDto> responses = new ArrayList<>();

		final Candidate candidate = candidateDao.getByEmail(candidateEmail);
		final List<CandidateEducation> educations = educationDao.getByCandidateId(candidate.getId());

		for (CandidateEducation education : educations) {
			final CandidateEducationResDto response = new CandidateEducationResDto();
			response.setDegreeId(education.getDegree().getId());
			response.setMajorId(education.getMajor().getId());
			responses.add(response);
		}

		return responses;
	}

}
