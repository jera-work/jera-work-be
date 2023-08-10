package com.lawencon.candidate.service;

import java.util.UUID;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.candidate.dao.CandidateDao;
import com.lawencon.candidate.dao.CandidateProfileDao;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.register.RegisterReqDto;
import com.lawencon.candidate.login.LoginReqDto;
import com.lawencon.candidate.model.Candidate;
import com.lawencon.candidate.model.CandidateProfile;

@Service
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
		final Supplier<String> id = () -> UUID.randomUUID().toString();
		final CandidateProfile candidateProfileDb = candidateProfileDao.saveNoLogin(candidateProfile, id);
		
		candidate.setCandidateProfile(candidateProfileDb);
		final Candidate candidateDb = candidateDao.saveNoLogin(candidate, id);
		
		final InsertResDto response = new InsertResDto();
		response.setId(candidateDb.getId());
		response.setMessage("Your profile : " + candidateDb.getCandidateProfile().getProfileName() + " has been created!");
		
		return response;
	}
	
	public Candidate login(LoginReqDto data) {
		final String candidateEmail = data.getUserEmail();
		final Candidate candidateLogin = candidateDao.getByEmail(candidateEmail);
		return candidateLogin;
	}
	
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		final Candidate detail = candidateDao.getByEmail(username);
//
//		if (detail != null) {
//			return new org.springframework.security.core.userdetails.User(username, detail.getCandidatePassword(),
//					new ArrayList<>());
//		}
//
//		throw new UsernameNotFoundException("Candidate not found!");
//	}

}
