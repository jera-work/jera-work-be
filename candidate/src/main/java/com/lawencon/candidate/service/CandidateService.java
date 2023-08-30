package com.lawencon.candidate.service;

import java.util.ArrayList;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.dao.CandidateDao;
import com.lawencon.candidate.dao.CandidateProfileDao;
import com.lawencon.candidate.dao.FileDao;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.UpdateResDto;
import com.lawencon.candidate.dto.candidate.CandidatePasswordUpdateReqDto;
import com.lawencon.candidate.dto.profile.CandidateProfileUpdateReqDto;
import com.lawencon.candidate.dto.register.RegisterReqDto;
import com.lawencon.candidate.login.LoginReqDto;
import com.lawencon.candidate.model.Candidate;
import com.lawencon.candidate.model.CandidateProfile;
import com.lawencon.candidate.model.File;
import com.lawencon.candidate.util.GenerateUtil;
import com.lawencon.security.principal.PrincipalServiceImpl;

@Service
public class CandidateService implements UserDetailsService {

	@Autowired
	private CandidateDao candidateDao;
	@Autowired
	private CandidateProfileDao candidateProfileDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private FileDao fileDao;
	@Autowired
	private PrincipalServiceImpl principalService;
	@Autowired
	private ApiService apiService;

	/* Register for Candidate */
	public InsertResDto register(RegisterReqDto data) {
		final InsertResDto response = new InsertResDto();
		try {
			ConnHandler.begin();
			final CandidateProfile candidateProfile = new CandidateProfile();
			candidateProfile.setProfileName(data.getProfileName());
			candidateProfile.setProfileCode(GenerateUtil.generateRandomCode());
			final Supplier<String> systemId = () -> candidateDao.getSystemId();
			final CandidateProfile candidateProfileDb = candidateProfileDao.saveNoLogin(candidateProfile, systemId);
			
			final String passwordEncoded = passwordEncoder.encode(data.getCandidatePassword());
			final Candidate candidate = new Candidate();
			candidate.setCandidateEmail(data.getCandidateEmail());
			candidate.setCandidatePassword(passwordEncoded);
			candidate.setCandidateProfile(candidateProfileDb);
			candidate.setCandidateCode(GenerateUtil.generateRandomCode());
			final Candidate candidateDb = candidateDao.saveNoLogin(candidate, systemId);

			data.setCandidateCode(candidateDb.getCandidateCode());
			data.setProfileCode(candidateProfileDb.getProfileCode());
			final HttpStatus adminResponse = apiService.writeTo("http://localhost:8081/candidates/register", data);

			if (adminResponse.equals(HttpStatus.CREATED)) {
				response.setId(candidateDb.getId());
				response.setMessage("Account created succesfully");

				ConnHandler.commit();
			} else {
				ConnHandler.rollback();

				throw new RuntimeException("Insert Failed");
			}
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

			final Candidate candidate = candidateDao.getById(principalService.getAuthPrincipal());
			final CandidateProfile profile = candidateProfileDao.getById(candidate.getCandidateProfile().getId());
			profile.setExpectedSalary(data.getExpectedSalary());
			profile.setGender(data.getGenderId());
			profile.setMarital(data.getMaritalId());
			profile.setNationality(data.getNationalityId());
			profile.setReligion(data.getReligionId());
			profile.setPhoneNumber(data.getPhoneNumber());
			profile.setProfileAddress(data.getProfileAddress());
			profile.setProfileName(data.getProfileName());

			File fileDb = new File();
			if (profile.getPhoto() != null) {
				final String oldPhotoId = profile.getPhoto().getId();
				if (data.getPhotoContent() != null && data.getPhotoContent() != "") {
					final File newPhoto = new File();
					newPhoto.setFileContent(data.getPhotoContent());
					newPhoto.setFileExt(data.getPhotoExt());
					fileDb = fileDao.saveAndFlush(newPhoto);
					profile.setPhoto(fileDb);
					fileDao.deleteById(oldPhotoId);
				}
			} else {
				if (data.getPhotoContent() != null && data.getPhotoContent() != "") {
					final File newPhoto = new File();
					newPhoto.setFileContent(data.getPhotoContent());
					newPhoto.setFileExt(data.getPhotoExt());
					fileDb = fileDao.saveAndFlush(newPhoto);
					profile.setPhoto(fileDb);
				}
			}

			final CandidateProfile profileDb = candidateProfileDao.saveAndFlush(profile);

			data.setCandidateEmail(candidate.getCandidateEmail());
			final HttpStatus status = apiService.putTo("http://localhost:8081/candidates/profile", data);
			final UpdateResDto response = new UpdateResDto();

			if (status.equals(HttpStatus.OK)) {
				response.setVer(profileDb.getVersion());
				response.setMessage("Profile has been updated!");
				ConnHandler.commit();
			} else {
				ConnHandler.rollback();

				throw new RuntimeException("Update Failed");
			}

			return response;
		} catch (Exception e) {
			e.printStackTrace();
			ConnHandler.rollback();
			return null;
		}
	}

	/* change password for candidate */
	public UpdateResDto changePassword(CandidatePasswordUpdateReqDto data) {
		ConnHandler.commit();
		final String id = principalService.getAuthPrincipal();
		final Candidate cdt = candidateDao.getById(id);
		if (cdt != null) {

			if (passwordEncoder.matches(data.getOldPassword(), cdt.getCandidatePassword())) {
				cdt.setCandidatePassword(passwordEncoder.encode(data.getNewPassword()));
				final Candidate cdtDb = candidateDao.saveAndFlush(cdt);
				final UpdateResDto response = new UpdateResDto();
				response.setMessage("Your password has been successfully changed!");
				response.setVer(cdtDb.getVersion());
				ConnHandler.commit();
				return response;
			}
		}
		throw new UsernameNotFoundException("Email / password salah");

	}

	/* Login for candidate */
	public Candidate login(LoginReqDto data) {
		final String candidateEmail = data.getUserEmail();
		final Candidate candidateLogin = candidateDao.getByEmail(candidateEmail);
		return candidateLogin;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final Candidate detail = candidateDao.getByEmail(username);

		if (detail != null) {
			return new org.springframework.security.core.userdetails.User(username, detail.getCandidatePassword(),
					new ArrayList<>());
		}

		throw new UsernameNotFoundException("Candidate not found!");
	}

}
