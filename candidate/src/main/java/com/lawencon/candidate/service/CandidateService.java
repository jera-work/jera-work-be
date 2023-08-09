package com.lawencon.candidate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lawencon.candidate.dao.CandidateDao;
import com.lawencon.candidate.dao.CandidateProfileDao;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.register.RegisterReqDto;
import com.lawencon.candidate.model.Candidate;
import com.lawencon.candidate.model.CandidateProfile;

public class CandidateService {
	
	@Autowired
	private CandidateDao candidateDao;
	@Autowired
	private CandidateProfileDao candidateProfileDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public InsertResDto register(RegisterReqDto data) {
		
		final String passwordEncoded = passwordEncoder.encode(data.getCandidatePassword());
		final Candidate candidate = new Candidate();
		candidate.setCandidateEmail(data.getCandidateEmail());
		candidate.setCandidatePassword(passwordEncoded);
		
		final CandidateProfile candidateProfile = new CandidateProfile();
		candidateProfile.setProfileName(data.getProfileName());
		final CandidateProfile candidateProfileDb = candidateProfileDao.saveAndFlush(candidateProfile);
		
		candidate.setCandidateProfile(candidateProfileDb);
		final Candidate candidateDb = candidateDao.saveAndFlush(candidate);
		
		final InsertResDto response = new InsertResDto();
		response.setId(candidateDb.getId());
		response.setMessage("Your profile : " + candidateDb.getCandidateProfile().getProfileName() + " has been created!");
		
		return response;
	}

}
