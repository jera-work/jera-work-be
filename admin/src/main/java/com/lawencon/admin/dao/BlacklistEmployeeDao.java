package com.lawencon.admin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.BlacklistEmployee;
import com.lawencon.admin.model.Candidate;
import com.lawencon.admin.model.CandidateProfile;
import com.lawencon.admin.model.Company;
import com.lawencon.admin.model.HiredEmployee;
import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

@Repository
public class BlacklistEmployeeDao extends AbstractJpaDao {
	
	public BlacklistEmployee getById(final Object id) {
		return super.getById(BlacklistEmployee.class, id);
	}

	public BlacklistEmployee getByIdRef(final Object id) {
		return super.getByIdRef(BlacklistEmployee.class, id);
	}

	public BlacklistEmployee getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(BlacklistEmployee.class, id);
	}
	
	public List<BlacklistEmployee> getAll() {
		return super.getAll(BlacklistEmployee.class);
	}

	public BlacklistEmployee save(BlacklistEmployee data) {
		return super.save(data);
	}

	public BlacklistEmployee saveAndFlush(BlacklistEmployee entity) {
		return super.saveAndFlush(entity);
	}

	public BlacklistEmployee saveNoLogin(BlacklistEmployee entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(BlacklistEmployee.class, entityId);
	}

	public List<BlacklistEmployee> getByCompany(int firstIndex, int endIndex, String companyId){
		final String sql = "SELECT "
				+ "	tbe.id, tc.id AS candidateId, tcp.profile_name, tc2.company_name "
				+ "FROM "
				+ "	t_blacklist_employee tbe "
				+ "INNER JOIN "
				+ "	t_hired_employee the ON tbe.employee_id "
				+ "INNER JOIN "
				+ "	t_candidate tc ON the.candidate_id = tc.id "
				+ "INNER JOIN "
				+ "	t_candidate_profile tcp ON tc.candidate_profile_id = tcp.id "
				+ "INNER JOIN "
				+ "	t_company tc2 ON tbe.company_id = tc2.id "
				+ "WHERE "
				+ "	tbe.company_id = :companyId";
		final List<?> blkObjs = ConnHandler.getManager()
				.createNativeQuery(sql)
				.setParameter("companyId", companyId)
				.setFirstResult(firstIndex)
				.setMaxResults(endIndex)
				.getResultList();
		
		final List<BlacklistEmployee> blacklistEmployees = new ArrayList<>();
		
		if (blkObjs.size() > 0) {
			for (Object blkObj:blkObjs) {
				final Object[] blkObjArr = (Object[]) blkObj;
				final BlacklistEmployee blacklistEmployee = new BlacklistEmployee();
				blacklistEmployee.setId(blkObjArr[0].toString());
				
				final Candidate candidate = new Candidate();
				candidate.setId(blkObjArr[1].toString());
				
				final CandidateProfile candidateProfile = new CandidateProfile();
				candidateProfile.setProfileName(blkObjArr[2].toString());
				candidate.setCandidateProfile(candidateProfile);
				
				final HiredEmployee hiredEmployee = new HiredEmployee();
				hiredEmployee.setCandidate(candidate);
				
				blacklistEmployee.setEmployee(hiredEmployee);
				
				final Company company = new Company();
				company.setCompanyName(blkObjArr[3].toString());
				blacklistEmployee.setCompany(company);
				
				blacklistEmployees.add(blacklistEmployee);
			}
		}
		
		return blacklistEmployees;
	}
	
	public BlacklistEmployee getByCompanyAndEmployee(String companyId, String employeeId) {
		final String sql = "SELECT "
				+ "	tbe.id "
				+ "FROM "
				+ "	t_blacklist_employee tbe "
				+ "WHERE "
				+ "	tbe.company_id = :companyId AND tbe.employee_id = :employeeId";
		
		try {
			final Object blkObj = ConnHandler.getManager()
					.createNativeQuery(sql)
					.setParameter("companyId", companyId)
					.setParameter("employeeId", employeeId)
					.getSingleResult();

			BlacklistEmployee blacklistEmployee = null;
			if(blkObj != null) {
				final Object[] blkObjArr = (Object[]) blkObj;

				if(blkObjArr.length > 0) {
					blacklistEmployee =  new BlacklistEmployee();
					blacklistEmployee.setId(blkObjArr[0].toString());
				}
			}
			return blacklistEmployee;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}