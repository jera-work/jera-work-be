package com.lawencon.candidate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.candidate.dao.CandidateDao;
import com.lawencon.candidate.dao.CandidateProfileDao;
import com.lawencon.candidate.dto.profile.CandidateProfileResDto;
import com.lawencon.candidate.model.Candidate;
import com.lawencon.candidate.model.CandidateProfile;
import com.lawencon.security.principal.PrincipalServiceImpl;

@Service
public class CandidateProfileService {
	
	@Autowired
	private CandidateDao candidateDao;
	@Autowired
	private PrincipalServiceImpl principalService;
	@Autowired
	private CandidateProfileDao profileDao;
	@Autowired
	private EmailEncoderService encoderService;
	@Autowired
	private ApiService apiService;

	public CandidateProfileResDto getProfile() {
		
		final Candidate candidate = candidateDao.getById(principalService.getAuthPrincipal());
		final String encodedEmail = encoderService.encodeEmail(candidate.getCandidateEmail());
		
		String url = "http://localhost:8081/candidates/?email=" + encodedEmail;
		final CandidateProfileResDto dto = apiService.getFrom(url, CandidateProfileResDto.class);
		
		final CandidateProfile profile = profileDao.getById(candidate.getCandidateProfile().getId());
		if(profile.getPhoto() != null) {
			dto.setPhotoId(profile.getPhoto().getId());			
		}
		
		return dto;
	}

}
