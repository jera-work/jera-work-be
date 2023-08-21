package com.lawencon.admin.service;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.CandidateDao;
import com.lawencon.admin.dao.CandidateProfileDao;
import com.lawencon.admin.dao.FileDao;
import com.lawencon.admin.dao.GenderDao;
import com.lawencon.admin.dao.MaritalDao;
import com.lawencon.admin.dao.NationalityDao;
import com.lawencon.admin.dao.ReligionDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.UpdateResDto;
import com.lawencon.admin.dto.candidate.CandidateInsertReqDto;
import com.lawencon.admin.dto.candidateprofile.CandidateProfileUpdateReqDto;
import com.lawencon.admin.model.Candidate;
import com.lawencon.admin.model.CandidateProfile;
import com.lawencon.admin.model.File;
import com.lawencon.base.ConnHandler;

@Service
public class CandidateService {

	@Autowired
	private CandidateDao candidateDao;
	@Autowired
	private CandidateProfileDao candidateProfileDao;
	@Autowired
	private GenderDao genderDao;
	@Autowired
	private MaritalDao maritalDao;
	@Autowired
	private NationalityDao nationalityDao;
	@Autowired
	private FileDao fileDao;
	@Autowired
	private ReligionDao religionDao;

	/* Register for Candidate */
	public InsertResDto insertCandidate(CandidateInsertReqDto data) {
		try {
			ConnHandler.begin();
			final CandidateProfile candidateProfile = new CandidateProfile();
			candidateProfile.setProfileName(data.getProfileName());
			final Supplier<String> systemId = () -> candidateDao.getSystemId();
			final CandidateProfile candidateProfileDb = candidateProfileDao.saveNoLogin(candidateProfile, systemId);

			final Candidate candidate = new Candidate();
			candidate.setCandidateEmail(data.getCandidateEmail());
			candidate.setCandidateProfile(candidateProfileDb);
			final Candidate candidateDb = candidateDao.saveNoLogin(candidate, systemId);

			final InsertResDto response = new InsertResDto();
			response.setId(candidateDb.getId());
			response.setMessage("Account created successfully");

			ConnHandler.commit();
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			ConnHandler.rollback();
			return null;
		}
	}

	/* Update profile for candidate */
	public UpdateResDto updateCandidateProfile(CandidateProfileUpdateReqDto data) {

		try {
			ConnHandler.begin();
			final Candidate candidate = candidateDao.getByEmail(data.getCandidateEmail());
			final CandidateProfile profile = candidateProfileDao.getById(candidate.getCandidateProfile().getId());

			profile.setExpectedSalary(data.getExpectedSalary());
			profile.setGender(genderDao.getByIdRef(data.getGenderId()));
			profile.setMarital(maritalDao.getByIdRef(data.getMaritalId()));
			profile.setNationality(nationalityDao.getByIdRef(data.getNationalityId()));
			profile.setPhoneNumber(data.getPhoneNumber());
			profile.setProfileAddress(data.getProfileAddress());
			profile.setReligion(religionDao.getByIdRef(data.getReligionId()));
			profile.setProfileName(data.getProfileName());
			
			if(data.getPhotoContent() != null) {
				final File photo = new File();
				photo.setFileContent(data.getPhotoContent());
				photo.setFileExt(data.getPhotoExt());
				final File photoDb = fileDao.saveAndFlush(photo);				
				profile.setPhoto(photoDb);
			}
			

			final CandidateProfile profileDb = candidateProfileDao.saveAndFlush(profile);

			final UpdateResDto response = new UpdateResDto();
			response.setVer(profileDb.getVersion());
			response.setMessage("Profile has been updated!");

			ConnHandler.commit();

			return response;
		} catch (Exception e) {
			e.printStackTrace();
			ConnHandler.rollback();
			return null;
		}
	}

	
	
	
	
	

}
