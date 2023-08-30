package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.CandidateDao;
import com.lawencon.admin.dao.CandidateEducationDao;
import com.lawencon.admin.dao.DegreeDao;
import com.lawencon.admin.dao.MajorDao;
import com.lawencon.admin.dto.DeleteResDto;
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
	@Autowired
	private EmailEncoderService encoderService;
	
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
				education.setEndYear(DateUtil.dateTimeParse(data.getEndYear()));
				education.setGpa(data.getGpa());
				education.setStartYear(DateUtil.dateTimeParse(data.getStartYear()));
				education.setInstitutionAddress(data.getInstitutionAddress());
				education.setInstitutionName(data.getInstitutionName());
				education.setMajor(majorDao.getByIdRef(data.getMajorId()));
				education.setEducationCode(data.getEducationCode());

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
		final String email = encoderService.decodeEmail(candidateEmail);
		final List<CandidateEducationResDto> responses = new ArrayList<>();

		final Candidate candidate = candidateDao.getByEmail(email);
		final List<CandidateEducation> educations = educationDao.getByCandidateId(candidate.getId());

		for (CandidateEducation education : educations) {
			final CandidateEducationResDto response = new CandidateEducationResDto();
			response.setDegreeId(education.getDegree().getDegreeName());
			response.setMajorId(education.getMajor().getMajorName());
			responses.add(response);
		}

		return responses;
	}
	
	/* get educations for admin */ 
	public List<CandidateEducationResDto> getEducationsByCandidateId(String candidateId) {
		final List<CandidateEducationResDto> responses = new ArrayList<>();
		final Candidate candidate = candidateDao.getById(candidateId);
		final List<CandidateEducation> educations = educationDao.getByCandidateId(candidate.getId());

		for (CandidateEducation education : educations) {
			final CandidateEducationResDto response = new CandidateEducationResDto();
			response.setDegreeId(education.getDegree().getDegreeName());
			response.setMajorId(education.getMajor().getMajorName());
			response.setId(education.getId());
			response.setEndYear(DateUtil.yearFormat(education.getEndYear()));
			response.setGpa(education.getGpa());
			response.setInstitutionAddress(education.getInstitutionAddress());
			response.setInstitutionName(education.getInstitutionName());
			response.setStartYear(DateUtil.yearFormat(education.getStartYear()));
			responses.add(response);
		}

		return responses;
	}
	
	/* delete educations for candidate */
	public DeleteResDto deleteEducation(String eduCode) {
		ConnHandler.begin();
		
		final DeleteResDto response = new DeleteResDto();
		final CandidateEducation edu = educationDao.getByCode(eduCode);
		final Boolean result = educationDao.deleteById(edu.getId());
		
		if(result) {
			response.setMessage("deleted");
			ConnHandler.commit();
		} else {
			ConnHandler.rollback();
		}
		
		return response;
	}

}
