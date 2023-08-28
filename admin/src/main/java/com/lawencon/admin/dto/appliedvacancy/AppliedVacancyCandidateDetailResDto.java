package com.lawencon.admin.dto.appliedvacancy;

import java.util.List;

import com.lawencon.admin.dto.candidatedocument.CandidateDocumentResDto;
import com.lawencon.admin.dto.candidateexperience.CandidateExperienceResDto;
import com.lawencon.admin.dto.candidateskill.CandidateSkillResDto;
import com.lawencon.admin.dto.education.CandidateEducationResDto;

public class AppliedVacancyCandidateDetailResDto {

	private String id;
	private String candidateName;
	private String profileAddress;
	private String phoneNumber;
	private String expectedSalary;
	private String genderName;
	private String maritalName;
	private String nationalityName;
	private String religionName;
	private String photoId;
	private String appliedStatus;
	private String appliedStatusId;
	private String appliedProgress;
	private String appliedProgressId;
	private String picHrId;
	private String picHrName;
	private String picUserId;
	private String picUserName;
	private String jobTitle;
	private List<CandidateDocumentResDto> documents;
	private List<CandidateExperienceResDto> experiences;
	private List<CandidateEducationResDto> educations;
	private List<CandidateSkillResDto> skills;

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getAppliedStatusId() {
		return appliedStatusId;
	}

	public void setAppliedStatusId(String appliedStatusId) {
		this.appliedStatusId = appliedStatusId;
	}

	public String getAppliedProgressId() {
		return appliedProgressId;
	}

	public void setAppliedProgressId(String appliedProgressId) {
		this.appliedProgressId = appliedProgressId;
	}

	public List<CandidateDocumentResDto> getDocuments() {
		return documents;
	}

	public void setDocuments(List<CandidateDocumentResDto> documents) {
		this.documents = documents;
	}

	public List<CandidateExperienceResDto> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<CandidateExperienceResDto> experiences) {
		this.experiences = experiences;
	}

	public List<CandidateEducationResDto> getEducations() {
		return educations;
	}

	public void setEducations(List<CandidateEducationResDto> educations) {
		this.educations = educations;
	}

	public List<CandidateSkillResDto> getSkills() {
		return skills;
	}

	public void setSkills(List<CandidateSkillResDto> skills) {
		this.skills = skills;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getProfileAddress() {
		return profileAddress;
	}

	public void setProfileAddress(String profileAddress) {
		this.profileAddress = profileAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getExpectedSalary() {
		return expectedSalary;
	}

	public void setExpectedSalary(String expectedSalary) {
		this.expectedSalary = expectedSalary;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	public String getMaritalName() {
		return maritalName;
	}

	public void setMaritalName(String maritalName) {
		this.maritalName = maritalName;
	}

	public String getNationalityName() {
		return nationalityName;
	}

	public void setNationalityName(String nationalityName) {
		this.nationalityName = nationalityName;
	}

	public String getReligionName() {
		return religionName;
	}

	public void setReligionName(String religionName) {
		this.religionName = religionName;
	}

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	public String getAppliedStatus() {
		return appliedStatus;
	}

	public void setAppliedStatus(String appliedStatus) {
		this.appliedStatus = appliedStatus;
	}

	public String getAppliedProgress() {
		return appliedProgress;
	}

	public void setAppliedProgress(String appliedProgress) {
		this.appliedProgress = appliedProgress;
	}

	public String getPicHrId() {
		return picHrId;
	}

	public void setPicHrId(String picHrId) {
		this.picHrId = picHrId;
	}

	public String getPicHrName() {
		return picHrName;
	}

	public void setPicHrName(String picHrName) {
		this.picHrName = picHrName;
	}

	public String getPicUserId() {
		return picUserId;
	}

	public void setPicUserId(String picUserId) {
		this.picUserId = picUserId;
	}

	public String getPicUserName() {
		return picUserName;
	}

	public void setPicUserName(String picUserName) {
		this.picUserName = picUserName;
	}

}
