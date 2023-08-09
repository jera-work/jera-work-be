package com.lawencon.candidate.dto.register;

import java.util.List;

import com.lawencon.candidate.dto.document.CandidateDocumentReqDto;
import com.lawencon.candidate.dto.education.CandidateEducationReqDto;
import com.lawencon.candidate.dto.experience.CandidateExperienceReqDto;

public class RegisterReqDto {
	
	private String candidateEmail;
	private String candidatePassword;
	private String profileName;
//	private String profileAddress;
//	private String phoneNumber;
//	private String expectedSalary;
//	private String photoContent;
//	private String photoExt;
//	private List<CandidateDocumentReqDto> documents;
//	private List<CandidateEducationReqDto> educations;
//	private List<CandidateExperienceReqDto> experiences;

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

//	public String getProfileAddress() {
//		return profileAddress;
//	}
//
//	public void setProfileAddress(String profileAddress) {
//		this.profileAddress = profileAddress;
//	}
//
//	public String getPhoneNumber() {
//		return phoneNumber;
//	}
//
//	public void setPhoneNumber(String phoneNumber) {
//		this.phoneNumber = phoneNumber;
//	}
//
//	public String getExpectedSalary() {
//		return expectedSalary;
//	}
//
//	public void setExpectedSalary(String expectedSalary) {
//		this.expectedSalary = expectedSalary;
//	}
//
//	public String getPhotoContent() {
//		return photoContent;
//	}
//
//	public void setPhotoContent(String photoContent) {
//		this.photoContent = photoContent;
//	}
//
//	public String getPhotoExt() {
//		return photoExt;
//	}
//
//	public void setPhotoExt(String photoExt) {
//		this.photoExt = photoExt;
//	}

//	public List<CandidateDocumentReqDto> getDocuments() {
//		return documents;
//	}
//
//	public void setDocuments(List<CandidateDocumentReqDto> documents) {
//		this.documents = documents;
//	}
//
//	public List<CandidateEducationReqDto> getEducations() {
//		return educations;
//	}
//
//	public void setEducations(List<CandidateEducationReqDto> educations) {
//		this.educations = educations;
//	}
//
//	public List<CandidateExperienceReqDto> getExperiences() {
//		return experiences;
//	}
//
//	public void setExperiences(List<CandidateExperienceReqDto> experiences) {
//		this.experiences = experiences;
//	}

}
