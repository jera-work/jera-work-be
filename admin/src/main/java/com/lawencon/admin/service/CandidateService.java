package com.lawencon.admin.service;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.CandidateDao;
import com.lawencon.admin.dao.CandidateDocumentDao;
import com.lawencon.admin.dao.CandidateEducationDao;
import com.lawencon.admin.dao.CandidateProfileDao;
import com.lawencon.admin.dao.DegreeDao;
import com.lawencon.admin.dao.DocumentTypeDao;
import com.lawencon.admin.dao.FileDao;
import com.lawencon.admin.dao.GenderDao;
import com.lawencon.admin.dao.MajorDao;
import com.lawencon.admin.dao.MaritalDao;
import com.lawencon.admin.dao.NationalityDao;
import com.lawencon.admin.dao.ReligionDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.UpdateResDto;
import com.lawencon.admin.dto.candidate.CandidateInsertReqDto;
import com.lawencon.admin.dto.candidateprofile.CandidateProfileUpdateReqDto;
import com.lawencon.admin.dto.document.CandidateDocumentCreateReqDto;
import com.lawencon.admin.dto.education.CandidateEducationCreateReqDto;
import com.lawencon.admin.model.Candidate;
import com.lawencon.admin.model.CandidateDocument;
import com.lawencon.admin.model.CandidateEducation;
import com.lawencon.admin.model.CandidateProfile;
import com.lawencon.admin.model.File;
import com.lawencon.base.ConnHandler;
import com.lawencon.security.principal.PrincipalServiceImpl;

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
	@Autowired
	private PrincipalServiceImpl principalService;
	@Autowired
	private DocumentTypeDao typeDao;
	@Autowired
	private CandidateDocumentDao docsDao;
	@Autowired
	private DegreeDao degreeDao;
	@Autowired
	private MajorDao majorDao;
	@Autowired
	private CandidateEducationDao educationDao;

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
			response.setMessage(
					"Your profile : " + candidateDb.getCandidateProfile().getProfileName() + " has been created!");

			ConnHandler.commit();
			return response;
		} catch (Exception e) {
			ConnHandler.rollback();
			return null;
		}
	}

	/* Update profile for candidate */
	public UpdateResDto updateCandidateProfile(CandidateProfileUpdateReqDto data) {

		try {
			ConnHandler.begin();
			final Candidate candidate = candidateDao.getById(principalService.getAuthPrincipal());
			final CandidateProfile profile = candidateProfileDao.getById(candidate.getCandidateProfile());

			final File photo = new File();
			photo.setFileContent(data.getFileContent());
			photo.setFileExt(data.getFileExt());
			final File photoDb = fileDao.save(photo);

			profile.setExpectedSalary(data.getExpectedSalary());
			profile.setGender(genderDao.getByIdRef(data.getGenderId()));
			profile.setMarital(maritalDao.getByIdRef(data.getMaritalId()));
			profile.setNationality(nationalityDao.getByIdRef(data.getNationalityId()));
			profile.setPhoneNumber(data.getPhoneNumber());
			profile.setPhoto(photoDb);
			profile.setProfileAddress(data.getProfileAddress());
			profile.setReligion(religionDao.getByIdRef(data.getReligionId()));

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
				doc.setDocumentType(typeDao.getByIdRef(data.getDocumentTypeId()));
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
				education.setDegree(degreeDao.getByIdRef(data.getDegreeId()));
				education.setEndYear(data.getEndYear());
				education.setGpa(data.getGpa());
				education.setStartYear(data.getStartYear());
				education.setInstitutionAddress(data.getInstitutionAddress());
				education.setInstitutionName(data.getInstitutionName());
				education.setMajor(majorDao.getByIdRef(data.getMajorId()));

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

}
