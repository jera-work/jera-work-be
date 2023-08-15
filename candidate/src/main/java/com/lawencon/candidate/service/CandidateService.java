package com.lawencon.candidate.service;

import java.util.ArrayList;
import java.util.List;
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
import com.lawencon.candidate.dao.CandidateDocumentDao;
import com.lawencon.candidate.dao.CandidateEducationDao;
import com.lawencon.candidate.dao.CandidateProfileDao;
import com.lawencon.candidate.dao.FileDao;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.UpdateResDto;
import com.lawencon.candidate.dto.document.CandidateDocumentCreateReqDto;
import com.lawencon.candidate.dto.education.CandidateEducationCreateReqDto;
import com.lawencon.candidate.dto.profile.CandidateProfileUpdateReqDto;
import com.lawencon.candidate.dto.register.RegisterReqDto;
import com.lawencon.candidate.login.LoginReqDto;
import com.lawencon.candidate.model.Candidate;
import com.lawencon.candidate.model.CandidateDocument;
import com.lawencon.candidate.model.CandidateEducation;
import com.lawencon.candidate.model.CandidateProfile;
import com.lawencon.candidate.model.File;
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
	private CandidateDocumentDao docsDao;
	@Autowired
	private CandidateEducationDao educationDao;
	@Autowired
	private ApiService apiService;

	/* Register for Candidate */
	public InsertResDto register(RegisterReqDto data) {
		final InsertResDto response = new InsertResDto();
		try {
			ConnHandler.begin();
			final CandidateProfile candidateProfile = new CandidateProfile();
			candidateProfile.setProfileName(data.getProfileName());
			final Supplier<String> systemId = () -> candidateDao.getSystemId();
			final CandidateProfile candidateProfileDb = candidateProfileDao.saveNoLogin(candidateProfile, systemId);
			
			final String passwordEncoded = passwordEncoder.encode(data.getCandidatePassword());
			final Candidate candidate = new Candidate();
			candidate.setCandidateEmail(data.getCandidateEmail());
			candidate.setCandidatePassword(passwordEncoded);
			candidate.setCandidateProfile(candidateProfileDb);
			final Candidate candidateDb = candidateDao.saveNoLogin(candidate, systemId);

			final HttpStatus adminResponse = apiService.writeTo("http://localhost:8081/candidates/register", data);

			if(adminResponse.equals(HttpStatus.CREATED)) {
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
			
			final File photo = new File();
			photo.setFileContent(data.getFileContent());
			photo.setFileExt(data.getFileExt());
			final File photoDb = fileDao.saveAndFlush(photo);
			
			final Candidate candidate = candidateDao.getById(principalService.getAuthPrincipal());
			final CandidateProfile profile = candidateProfileDao.getById(candidate.getCandidateProfile());
			profile.setExpectedSalary(data.getExpectedSalary());
			profile.setGender(data.getGenderId());
			profile.setMarital(data.getMaritalId());
			profile.setNationality(data.getNationalityId());
			profile.setReligion(data.getReligionId());
			profile.setPhoneNumber(data.getPhoneNumber());
			profile.setPhoto(photoDb);
			profile.setProfileAddress(data.getProfileAddress());
			final CandidateProfile profileDb = candidateProfileDao.saveAndFlush(profile);
			
			final UpdateResDto response = new UpdateResDto();
			response.setVer(profileDb.getVersion());
			response.setMessage("Profile has been updated!");
			ConnHandler.commit();

			return response;
		} catch (Exception e) {
			ConnHandler.rollback();
			return null;
		}
	}

	/* Insert documents for candidate */
	public InsertResDto insertCandidateDocs(List<CandidateDocumentCreateReqDto> datas) {
		final Candidate candidate = candidateDao.getByIdRef(principalService.getAuthPrincipal());

		try {
			ConnHandler.begin();
			InsertResDto response = null;
			for (CandidateDocumentCreateReqDto data : datas) {
				final File file = new File();
				file.setFileContent(data.getFileContent());
				file.setFileExt(data.getFileExt());
				final File fileDb = fileDao.save(file);

				final CandidateDocument doc = new CandidateDocument();
				doc.setCandidate(candidate);
				doc.setDocumentType(data.getDocumentTypeId());
				doc.setFile(fileDb);
				final CandidateDocument docDb = docsDao.save(doc);

				response = new InsertResDto();
				response.setId(docDb.getId());
				response.setMessage("Documents has been added!");
			}
			return response;
		} catch (Exception e) {
			ConnHandler.rollback();
			return null;
		}

	}

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
				education.setEndYear(data.getEndYear());
				education.setGpa(data.getGpa());
				education.setStartYear(data.getStartYear());
				education.setInstitutionAddress(data.getInstitutionAddress());
				education.setInstitutionName(data.getInstitutionName());
				education.setMajor(data.getMajorId());

				final CandidateEducation educationDb = educationDao.saveAndFlush(education);

				response = new InsertResDto();
				response.setId(educationDb.getId());
				response.setMessage("Educations has been added!");
			}
			return response;
		} catch (Exception e) {
			ConnHandler.rollback();
			return null;
		}
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
