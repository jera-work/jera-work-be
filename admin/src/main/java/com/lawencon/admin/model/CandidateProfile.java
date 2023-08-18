package com.lawencon.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_candidate_profile")
public class CandidateProfile extends BaseEntity{
	
	@Column(name = "profile_name", nullable = false, length = 50)
	private String profileName;
	
	@Column(name = "profile_address", nullable = true, length = 50)
	private String profileAddress;
	
	@Column(name = "phone_number", nullable = true, length = 14)
	private String phoneNumber;
	
	@Column(name = "expected_salary", nullable = true)
	private String expectedSalary;
	
	@OneToOne
	@JoinColumn(name = "gender_id", nullable = true)
	private Gender gender;
	
	@OneToOne
	@JoinColumn(name = "nationality_id", nullable = true)
	private Nationality nationality;
	
	@OneToOne
	@JoinColumn(name = "photo_id", nullable = true)
	private File photo;
	
	@OneToOne
	@JoinColumn(name = "marital_id", nullable = true)
	private Marital marital;
	
	@OneToOne
	@JoinColumn(name = "religion_id", nullable = true)
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
