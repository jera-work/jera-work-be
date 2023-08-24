package com.lawencon.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.CandidateDao;
import com.lawencon.admin.dao.CandidateProfileDao;
import com.lawencon.admin.dto.candidateprofile.CandidateProfileResDto;
import com.lawencon.admin.model.Candidate;
import com.lawencon.admin.model.CandidateProfile;

@Service
public class CandidateProfileService {
	
	@Autowired
	private CandidateProfileDao profileDao;
	@Autowired
	private CandidateDao candidateDao;
	@Autowired
	private EmailEncoderService encoderService;
	
	public CandidateProfileResDto getProfile(String candidateEmail) {
		
		final String email = encoderService.decodeEmail(candidateEmail);
		final Candidate candidate = candidateDao.getByEmail(email);
		final CandidateProfile profile = profileDao.getById(candidate.getCandidateProfile().getId());
		final CandidateProfileResDto response = new CandidateProfileResDto();
		response.setCandidateEmail(candidate.getCandidateEmail());
		response.setProfileName(profile.getProfileName());
		
		if(profile.getExpectedSalary() != null) {
			response.setExpectedSalary(profile.getExpectedSalary());			
		}
		if(profile.getGender() != null) {
			response.setGenderId(profile.getGender().getId());			
		}
		if(profile.getMarital() != null) {
			response.setMaritalId(profile.getMarital().getId());			
		}
		if(profile.getNationality() != null) {
			response.setNationalityId(profile.getNationality().getId());
		}
		if(profile.getPhoneNumber() != null) {
			response.setPhoneNumber(profile.getPhoneNumber());			
		}
		if(profile.getProfileAddress() != null) {
			response.setProfileAddress(profile.getProfileAddress());			
		}
		if(profile.getReligion() != null) {
			response.setReligionId(profile.getReligion().getId());			
		}
		if(profile.getPhoto() != null) {
			response.setPhotoId(profile.getPhoto().getId());
		}
		
		return response;
		
	}

}
