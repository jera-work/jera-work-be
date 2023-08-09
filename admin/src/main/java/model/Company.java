package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_company")
public class Company extends BaseEntity{
	
	@Column(name = "company_code", nullable = false, unique = true, length = 8)
	private String companyCode;
	
	@Column(name = "company_name", nullable = false, length = 30)
	private String companyName;
	
	@OneToOne
	@JoinColumn(name = "photo_id", nullable = false)
	private File photo;
	
	@Column(name = "address", nullable = false)
	private String address;
	
	@Column(name = "phone_number", nullable = false, length = 14)
	private String phoneNumber;
	
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public File getPhoto() {
		return photo;
	}
	public void setPhoto(File photo) {
		this.photo = photo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
