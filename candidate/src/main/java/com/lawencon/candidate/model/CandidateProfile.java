package com.lawencon.candidate.model;

import com.lawencon.base.BaseEntity;

public class CandidateProfile extends BaseEntity{
	private String profileName;
	private String profileAddress;
	private String phoneNumber;
	private String expectedSalary;
	private Gender gender;
	private Nationality nationality;
	private File photo;
	private Marital marital;
	private Religion religion;
	
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
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Nationality getNationality() {
		return nationality;
	}
	public void setNationality(Nationality nationality) {
		this.nationality = nationality;
	}
	public File getPhoto() {
		return photo;
	}
	public void setPhoto(File photo) {
		this.photo = photo;
	}
	public Marital getMarital() {
		return marital;
	}
	public void setMarital(Marital marital) {
		this.marital = marital;
	}
	public Religion getReligion() {
		return religion;
	}
	public void setReligion(Religion religion) {
		this.religion = religion;
	}
}
