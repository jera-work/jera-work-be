package com.lawencon.candidate.dto.register;

import java.util.List;

import com.lawencon.candidate.dto.document.CandidateDocumentReqDto;
import com.lawencon.candidate.dto.education.CandidateEducationReqDto;
import com.lawencon.candidate.dto.experience.CandidateExperienceReqDto;

public class RegisterReqDto {
	
	private String candidateEmail;
	private String candidatePassword;
	private String profileName;
	private String profileAddress;
	private String phoneNumber;
	private String expectedSalary;
	private String genderCode;
	private String genderName;
	private String nationalityCode;
	private String nationalityName;
	private String photoContent;
	private String photoExt;
	private String martialCode;
	private String maritalName;
	private String religionCode;
	private String religionName;
	private List<CandidateDocumentReqDto> documents;
	private List<CandidateEducationReqDto> educations;
	private List<CandidateExperienceReqDto> experiences;

	public String getCandidateEmail() {
		return candidateEmail;
	}

	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}

	public String getCandidatePassword() {
		return candidatePassword;
	}

	public void setCandidatePassword(String candidatePassword) {
		this.candidatePassword = candidatePassword;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
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

	public String getGenderCode() {
		return genderCode;
	}

	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	public String getNationalityCode() {
		return nationalityCode;
	}

	public void setNationalityCode(String nationalityCode) {
		this.nationalityCode = nationalityCode;
	}

	public String getNationalityName() {
		return nationalityName;
	}

	public void setNationalityName(String nationalityName) {
		this.nationalityName = nationalityName;
	}

	public String getPhotoContent() {
		return photoContent;
	}

	public void setPhotoContent(String photoContent) {
		this.photoContent = photoContent;
	}

	public String getPhotoExt() {
		return photoExt;
	}

	public void setPhotoExt(String photoExt) {
		this.photoExt = photoExt;
	}

	public String getMartialCode() {
		return martialCode;
	}

	public void setMartialCode(String martialCode) {
		this.martialCode = martialCode;
	}

	public String getMaritalName() {
		return maritalName;
	}

	public void setMaritalName(String maritalName) {
		this.maritalName = maritalName;
	}

	public String getReligionCode() {
		return religionCode;
	}

	public void setReligionCode(String religionCode) {
		this.religionCode = religionCode;
	}

	public String getReligionName() {
		return religionName;
	}

	public void setReligionName(String religionName) {
		this.religionName = religionName;
	}

	public List<CandidateDocumentReqDto> getDocuments() {
		return documents;
	}

	public void setDocuments(List<CandidateDocumentReqDto> documents) {
		this.documents = documents;
	}

	public List<CandidateEducationReqDto> getEducations() {
		return educations;
	}

	public void setEducations(List<CandidateEducationReqDto> educations) {
		this.educations = educations;
	}

	public List<CandidateExperienceReqDto> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<CandidateExperienceReqDto> experiences) {
		this.experiences = experiences;
	}

}
