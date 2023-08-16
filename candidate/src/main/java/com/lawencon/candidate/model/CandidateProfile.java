package com.lawencon.candidate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_candidate_profile")
public class CandidateProfile extends BaseEntity {

	@Column(name = "profile_name", length = 50, nullable = false)
	private String profileName;
	@Column(name = "profile_address", length = 50, nullable = true)
	private String profileAddress;
	@Column(name = "phone_number", length = 14, nullable = true)
	private String phoneNumber;
	@Column(name = "expected_salary", nullable = true)
	private String expectedSalary;

	@Column(name = "gender_id", nullable = true)
	private String gender;

	@Column(name = "nationality_id", nullable = true)
	private String nationality;

	@OneToOne
	@JoinColumn(name = "photo_id", nullable = true)
	private File photo;

	@Column(name = "marital_id", nullable = true)
	private String marital;

	@Column(name = "religion_id", nullable = true)
	private String religion;

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public String getMarital() {
		return marital;
	}

	public void setMarital(String marital) {
		this.marital = marital;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

}
