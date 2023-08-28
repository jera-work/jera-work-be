package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.lawencon.admin.constant.AppliedProgressCode;
import com.lawencon.admin.constant.AppliedStatusCode;
import com.lawencon.admin.dao.AppliedProgressDao;
import com.lawencon.admin.dao.AppliedStatusDao;
import com.lawencon.admin.dao.AppliedVacancyDao;
import com.lawencon.admin.dao.BlacklistEmployeeDao;
import com.lawencon.admin.dao.CandidateDao;
import com.lawencon.admin.dao.HiredEmployeeDao;
import com.lawencon.admin.dao.JobVacancyDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.UpdateResDto;
import com.lawencon.admin.dto.appliedstatus.UpdateStatusReqDto;
import com.lawencon.admin.dto.appliedvacancy.AppliedVacancyAdminResDto;
import com.lawencon.admin.dto.appliedvacancy.AppliedVacancyCandidateDetailResDto;
import com.lawencon.admin.dto.appliedvacancy.AppliedVacancyProgressResDto;
import com.lawencon.admin.dto.appliedvacancy.AppliedVacancyResDto;
import com.lawencon.admin.dto.appliedvacancy.InsertAppliedVacancyReqDto;
import com.lawencon.admin.dto.appliedvacancy.UpdateProgressReqDto;
import com.lawencon.admin.dto.candidatedocument.CandidateDocumentResDto;
import com.lawencon.admin.dto.candidateexperience.CandidateExperienceResDto;
import com.lawencon.admin.dto.candidateskill.CandidateSkillResDto;
import com.lawencon.admin.dto.education.CandidateEducationResDto;
import com.lawencon.admin.exception.CustomException;
import com.lawencon.admin.model.AppliedProgress;
import com.lawencon.admin.model.AppliedStatus;
import com.lawencon.admin.model.AppliedVacancy;
import com.lawencon.admin.model.Candidate;
import com.lawencon.admin.model.HiredEmployee;
import com.lawencon.admin.model.JobVacancy;
import com.lawencon.admin.util.DateUtil;
import com.lawencon.base.ConnHandler;

@Service
public class AppliedVacancyService {

	@Autowired
	private ApiService apiService;
	@Autowired
	private EmailEncoderService emailEncoderService;
	@Autowired
	private AppliedVacancyDao appliedVacancyDao;
	@Autowired
	private CandidateDao candidateDao;
	@Autowired
	private JobVacancyDao jobVacancyDao;
	@Autowired
	private AppliedProgressDao progressDao;
	@Autowired
	private AppliedStatusDao statusDao;
	@Autowired
	private BlacklistEmployeeDao blacklistEmployeeDao;
	@Autowired
	private HiredEmployeeDao hiredEmployeeDao;
	@Autowired
	private CandidateDocumentService docsService;
	@Autowired
	private CandidateExperienceService expService;
	@Autowired
	private CandidateEducationService eduService;
	@Autowired
	private CandidateSkillService skillService;

	public InsertResDto insertAppliedVacancy(InsertAppliedVacancyReqDto data) {

		final InsertResDto response = new InsertResDto();

		ConnHandler.begin();
		try {
			final JobVacancy job = jobVacancyDao.getByCode(data.getJobVacancyCode());
			final Candidate candidate = candidateDao.getByEmail(data.getCandidateEmail());

			if (hiredEmployeeDao.getByCandidate(job.getCompany().getId(), candidate.getId()) != null) {
				final HiredEmployee hiredEmployee = hiredEmployeeDao.getByCandidate(job.getCompany().getId(),
						candidate.getId());
				if (blacklistEmployeeDao.getByCompanyAndEmployee(job.getCompany().getId(),
						hiredEmployee.getId()) == null) {
					final AppliedVacancy appliedVacancy = new AppliedVacancy();
					appliedVacancy.setCandidate(candidate);
					appliedVacancy
							.setAppliedProgress(progressDao.getByCode(AppliedProgressCode.APPLICATION.progressCode));
					appliedVacancy.setAppliedStatus(statusDao.getByCode(AppliedStatusCode.ACTIVE.statusCode));
					appliedVacancy.setJobVacancy(job);
					final AppliedVacancy appliedVacancyDb = appliedVacancyDao.save(appliedVacancy);

					ConnHandler.commit();
					if (appliedVacancyDb != null) {
						response.setId(appliedVacancyDb.getId());
						response.setMessage("You have applied to this job!");
					} else {
						throw new CustomException();
					}
				}
			} else {
				final AppliedVacancy appliedVacancy = new AppliedVacancy();
				appliedVacancy.setCandidate(candidate);
				appliedVacancy.setAppliedProgress(progressDao.getByCode(AppliedProgressCode.APPLICATION.progressCode));
				appliedVacancy.setAppliedStatus(statusDao.getByCode(AppliedStatusCode.ACTIVE.statusCode));
				appliedVacancy.setJobVacancy(job);
				final AppliedVacancy appliedVacancyDb = appliedVacancyDao.save(appliedVacancy);

				ConnHandler.commit();
				if (appliedVacancyDb != null) {
					response.setId(appliedVacancyDb.getId());
					response.setMessage("You have applied to this job!");
				} else {
					throw new CustomException();
				}
			}

			return response;
		} catch (Exception e) {
			e.printStackTrace();
			ConnHandler.rollback();
			return null;
		}

	}

	public UpdateResDto changeAppliedStatusProgress(UpdateProgressReqDto data) {
		final UpdateResDto response = new UpdateResDto();

		ConnHandler.begin();
		final AppliedProgress appliedProgress = progressDao.getById(data.getAppliedProgressId());

		final AppliedVacancy appliedVacancy = appliedVacancyDao.getById(data.getAppliedVacancyId());
		appliedVacancy.setAppliedProgress(appliedProgress);

		final AppliedVacancy updatedAppliedVacancy = appliedVacancyDao.saveAndFlush(appliedVacancy);

		data.setJobVacancyCode(appliedVacancy.getJobVacancy().getVacancyCode());
		data.setCandidateEmail(appliedVacancy.getCandidate().getCandidateEmail());

		final HttpStatus responseCandidate = apiService.putTo("http://localhost:8080/applied", data);

		if (responseCandidate.equals(HttpStatus.OK)) {
			response.setVer(updatedAppliedVacancy.getVersion());
			response.setMessage("Progress updated successfully");
			ConnHandler.commit();
		} else {
			ConnHandler.rollback();

			throw new RuntimeException("Insert Failed");
		}

		return response;
	}
	
	public UpdateResDto changeAppliedStatus(UpdateStatusReqDto data) {
		final UpdateResDto response = new UpdateResDto();

		ConnHandler.begin();
		final AppliedStatus appliedStatus = statusDao.getById(data.getAppliedStatusId());

		final AppliedVacancy appliedVacancy = appliedVacancyDao.getById(data.getAppliedVacancyId());
		appliedVacancy.setAppliedStatus(appliedStatus);

		final AppliedVacancy updatedAppliedVacancy = appliedVacancyDao.saveAndFlush(appliedVacancy);

		data.setJobVacancyCode(appliedVacancy.getJobVacancy().getVacancyCode());
		data.setCandidateEmail(appliedVacancy.getCandidate().getCandidateEmail());

		final HttpStatus responseCandidate = apiService.putTo("http://localhost:8080/applied", data);

		if (responseCandidate.equals(HttpStatus.OK)) {
			response.setVer(updatedAppliedVacancy.getVersion());
			response.setMessage("Status updated successfully");
			ConnHandler.commit();
		} else {
			ConnHandler.rollback();

			throw new RuntimeException("Insert Failed");
		}

		return response;
	}

	public List<AppliedVacancyResDto> getByCandidateId(String email, int startIndex, int endIndex) {
		final String candidateEmail = emailEncoderService.decodeEmail(email);
		final Candidate candidate = candidateDao.getByEmail(candidateEmail);

		final List<AppliedVacancyResDto> responses = new ArrayList<>();

		appliedVacancyDao.getByCandidateId(candidate.getId(), startIndex, endIndex).forEach(av -> {
			final AppliedVacancyResDto response = new AppliedVacancyResDto();

			response.setId(av.getId());
			response.setJobVacancyId(av.getJobVacancy().getId());
			response.setJobVacancyCode(av.getJobVacancy().getVacancyCode());
			response.setAppliedProgressCode(av.getAppliedProgress().getProgressCode());
			response.setAppliedProgressName(av.getAppliedProgress().getProgressName());
			response.setAppliedStatusCode(av.getAppliedStatus().getStatusCode());
			response.setAppliedStatusName(av.getAppliedStatus().getStatusName());
			response.setCreatedAt(DateUtil.dateTimeFormatIso(av.getCreatedAt()));
			responses.add(response);

		});

		return responses;
	}

	public List<AppliedVacancyAdminResDto> getByProgress(String progressId) {
		final List<AppliedVacancyAdminResDto> responses = new ArrayList<>();

		appliedVacancyDao.getByProgressId(progressId).forEach(av -> {
			final AppliedVacancyAdminResDto response = new AppliedVacancyAdminResDto();
			response.setId(av.getId());
			response.setProfileName(av.getCandidate().getCandidateProfile().getProfileName());
			response.setStatusName(av.getAppliedStatus().getStatusName());
			response.setProgressName(av.getAppliedProgress().getProgressName());
			response.setCreatedAt(DateUtil.dateTimeFormat(av.getCreatedAt()));

			responses.add(response);
		});

		return responses;
	}

	public List<AppliedVacancyAdminResDto> getByJobVacancyId(String jobId) {
		final List<AppliedVacancyAdminResDto> responses = new ArrayList<>();

		appliedVacancyDao.getByJobVacancyId(jobId).forEach(av -> {
			final AppliedVacancyAdminResDto response = new AppliedVacancyAdminResDto();
			response.setId(av.getId());
			response.setProfileName(av.getCandidate().getCandidateProfile().getProfileName());
			response.setStatusName(av.getAppliedStatus().getStatusName());
			response.setProgressName(av.getAppliedProgress().getProgressName());
			response.setCreatedAt(DateUtil.dateTimeFormat(av.getCreatedAt()));

			responses.add(response);
		});

		return responses;
	}

	public AppliedVacancyCandidateDetailResDto getAppliedCandidateDetail(String appliedId) {
		final AppliedVacancyCandidateDetailResDto response = new AppliedVacancyCandidateDetailResDto();
		final AppliedVacancy applied = appliedVacancyDao.getById(appliedId);
		final Candidate candidate = candidateDao.getById(applied.getCandidate().getId());
		
		final List<CandidateDocumentResDto> docsDto = docsService.getDocumentsByCandidateId(candidate.getId());
		final List<CandidateExperienceResDto> expsDto = expService.getExperiencesByCandidateId(candidate.getId());
		final List<CandidateEducationResDto> edusDto = eduService.getEducationsByCandidateId(candidate.getId());
		final List<CandidateSkillResDto> skillsDto = skillService.getSkillsByCandidateId(candidate.getId());
	
		response.setAppliedProgress(applied.getAppliedProgress().getProgressName());
		response.setAppliedProgressId(applied.getAppliedProgress().getId());
		response.setAppliedProgressCode(applied.getAppliedProgress().getProgressCode());
		response.setAppliedStatus(applied.getAppliedStatus().getStatusName());
		response.setAppliedStatusId(applied.getAppliedStatus().getId());
		response.setAppliedStatusCode(applied.getAppliedStatus().getStatusCode());
		response.setCandidateName(candidate.getCandidateProfile().getProfileName());
		response.setExpectedSalary(candidate.getCandidateProfile().getExpectedSalary());
		response.setGenderName(candidate.getCandidateProfile().getGender().getGenderName());
		response.setId(appliedId);
		response.setMaritalName(candidate.getCandidateProfile().getMarital().getMaritalName());
		response.setNationalityName(candidate.getCandidateProfile().getNationality().getNationalityName());
		response.setPhoneNumber(candidate.getCandidateProfile().getPhoneNumber());
		response.setPhotoId(candidate.getCandidateProfile().getPhoto().getId());
		response.setPicHrId(applied.getJobVacancy().getPicHr().getId());
		response.setPicHrName(applied.getJobVacancy().getPicHr().getProfile().getProfileName());
		response.setPicUserId(applied.getJobVacancy().getPicUser().getId());
		response.setPicUserName(applied.getJobVacancy().getPicUser().getProfile().getProfileName());
		response.setProfileAddress(candidate.getCandidateProfile().getProfileAddress());
		response.setReligionName(candidate.getCandidateProfile().getReligion().getReligionName());
		response.setJobTitle(applied.getJobVacancy().getVacancyTitle());
		response.setDocuments(docsDto);
		response.setExperiences(expsDto);
		response.setEducations(edusDto);
		response.setSkills(skillsDto);

		return response;
	}
	
	public AppliedVacancyProgressResDto getAppliedByJobAndCandidate(String jobCode, String email) {
		final AppliedVacancyProgressResDto response = new AppliedVacancyProgressResDto();
		final String candidateEmail = emailEncoderService.decodeEmail(email);

		final AppliedVacancy appliedVacancy = appliedVacancyDao.getByJobVacancyAndCandidate(
				jobVacancyDao.getByCode(jobCode).getId(), candidateDao.getByEmail(candidateEmail).getId());
		
		response.setProgressCode(appliedVacancy.getAppliedProgress().getProgressCode());
		return response;
	}
}
