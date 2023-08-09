package model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_blacklist_employee")
public class BlacklistEmployee extends BaseEntity {
	
	@OneToOne
	@JoinColumn(name = "employee_id", nullable = false, unique = true)
	private Candidate employee;
	
	@OneToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;
	
	public Candidate getEmployee() {
		return employee;
	}
	public void setEmployee(Candidate employee) {
		this.employee = employee;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
}
