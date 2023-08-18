package com.lawencon.admin.service;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.CandidateDao;
import com.lawencon.admin.dao.CandidateDocumentDao;
import com.lawencon.admin.dao.CandidateEducationDao;
import com.lawencon.admin.dao.CandidateExperienceDao;
import com.lawencon.admin.dao.CandidateProfileDao;
import com.lawencon.admin.dao.CandidateSkillDao;
import com.lawencon.admin.dao.DegreeDao;
import com.lawencon.admin.dao.DocumentTypeDao;
import com.lawencon.admin.dao.FileDao;
import com.lawencon.admin.dao.GenderDao;
import com.lawencon.admin.dao.MajorDao;
import com.lawencon.admin.dao.MaritalDao;
import com.lawencon.admin.dao.NationalityDao;
import com.lawencon.admin.dao.ReligionDao;
import com.lawencon.admin.dao.SkillDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.UpdateResDto;
import com.lawencon.admin.dto.candidate.CandidateInsertReqDto;
import com.lawencon.admin.dto.candidateprofile.CandidateProfileUpdateReqDto;
import com.lawencon.admin.dto.candidateskill.CandidateSkillReqDto;
import com.lawencon.admin.dto.document.CandidateDocumentCreateReqDto;
import com.lawencon.admin.dto.education.CandidateEducationCreateReqDto;
import com.lawencon.admin.dto.experience.CandidateExperienceReqDto;
import com.lawencon.admin.model.Candidate;
import com.lawencon.admin.model.CandidateDocument;
import com.lawencon.admin.model.CandidateEducation;
import com.lawencon.admin.model.CandidateExperience;
import com.lawencon.admin.model.CandidateProfile;
import com.lawencon.admin.model.CandidateSkill;
import com.lawencon.admin.model.File;
import com.lawencon.admin.util.DateUtil;
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
	@Autowired
	private CandidateExperienceDao candidateExperienceDao;
	@Autowired
	private CandidateSkillDao candidateSkillDao;
	@Autowired
	private SkillDao skillDao;

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

	/* Insert documents for candidate */
	public InsertResDto insertCandidateDocs(List<CandidateDocumentCreateReqDto> datas) {

		try {
			ConnHandler.begin();
			InsertResDto response = null;
			for (CandidateDocumentCreateReqDto data : datas) {
				final Candidate candidate = candidateDao.getByEmail(data.getCandidateEmail());
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
			ConnHandler.commit();
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			ConnHandler.rollback();
			return null;
		}

	}

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
				education.setEndYear(DateUtil.dateParse(data.getEndYear()));
				education.setGpa(data.getGpa());
				education.setStartYear(DateUtil.dateParse(data.getStartYear()));
				education.setInstitutionAddress(data.getInstitutionAddress());
				education.setInstitutionName(data.getInstitutionName());
				education.setMajor(majorDao.getByIdRef(data.getMajorId()));

				final CandidateEducation educationDb = educationDao.saveAndFlush(education);

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
	
	/* Insert experiences for candidate */
	public InsertResDto createExperience(List<CandidateExperienceReqDto> data) {
		final InsertResDto response = new InsertResDto();

		ConnHandler.begin();
		try {
			if (data.size() > 0) {
				for (int i = 0; i < data.size(); i++) {
					final CandidateExperience candidateExperience = new CandidateExperience();

					final Candidate candidate = candidateDao.getByEmail(data.get(i).getCandidateEmail());
					candidateExperience.setCandidate(candidate);

					candidateExperience.setFormerInstitution(data.get(i).getFormerInstitution());
					candidateExperience.setFormerPosition(data.get(i).getFormerPosition());
					candidateExperience.setFormerJobdesk(data.get(i).getFormerJobdesk());
					candidateExperience.setFormerLocation(data.get(i).getFormerLocation());
					candidateExperience.setStartDate(DateUtil.dateParse(data.get(i).getStartDate()));
					candidateExperience.setEndDate(DateUtil.dateParse(data.get(i).getEndDate()));

					candidateExperienceDao.save(candidateExperience);
					data.get(i).setCandidateEmail(candidate.getCandidateEmail());
				}
			}

		} catch (Exception e) {
			ConnHandler.rollback();
		}

		return response;
	}
	
	/* insert skills for candidate */
	public InsertResDto createSkill(List<CandidateSkillReqDto> datas) {
		final InsertResDto response = new InsertResDto();
		
		ConnHandler.begin();
		try {
			for (CandidateSkillReqDto data : datas) {
				final Candidate candidate = candidateDao.getByEmail(data.getCandidateEmail());
				final CandidateSkill skill = new CandidateSkill();
				skill.setCandidate(candidate);
				skill.setSkill(skillDao.getById(data.getSkillId()));
				final CandidateSkill skillDb = candidateSkillDao.save(skill);
				
				data.setCandidateEmail(candidate.getCandidateEmail());
				response.setId(skillDb.getId());
			}
			response.setMessage("Skill choosen successfully");
			ConnHandler.commit();
			
		} catch (Exception e) {
			ConnHandler.rollback();
		}
		return response;
	}

}
