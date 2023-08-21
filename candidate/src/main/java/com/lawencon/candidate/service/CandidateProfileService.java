package com.lawencon.candidate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lawencon.candidate.dao.CandidateDao;
import com.lawencon.candidate.dao.CandidateProfileDao;
import com.lawencon.candidate.dto.profile.CandidateProfileResDto;
import com.lawencon.candidate.model.Candidate;
import com.lawencon.candidate.model.CandidateProfile;
import com.lawencon.config.JwtConfig;
import com.lawencon.security.principal.PrincipalServiceImpl;

@Service
public class CandidateProfileService {
	
	@Autowired
	private CandidateDao candidateDao;
	@Autowired
	private PrincipalServiceImpl principalService;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private CandidateProfileDao profileDao;

	@SuppressWarnings("null")
	public CandidateProfileResDto getProfile() {
		
		final Candidate candidate = candidateDao.getById(principalService.getAuthPrincipal());
		String url = "http://localhost:8081/candidates/?email=" + candidate.getCandidateEmail();
		
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(JwtConfig.get());

		final RequestEntity<Void> request = RequestEntity.get(url).headers(headers).build();

		final ResponseEntity<CandidateProfileResDto> response = restTemplate.exchange(request, CandidateProfileResDto.class);

		final CandidateProfileResDto dto = response.getBody();
		final CandidateProfile profile = profileDao.getById(candidate.getCandidateProfile().getId());
		if(dto.getPhotoId() != null) {
			dto.setPhotoId(profile.getPhoto().getId());			
		}
		
		return dto;
	}

}
