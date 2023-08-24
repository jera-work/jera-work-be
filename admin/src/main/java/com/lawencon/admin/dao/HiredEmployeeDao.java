package com.lawencon.admin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.Candidate;
import com.lawencon.admin.model.CandidateProfile;
import com.lawencon.admin.model.Company;
import com.lawencon.admin.model.HiredEmployee;
import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

@Repository
public class HiredEmployeeDao extends AbstractJpaDao {
	
	public HiredEmployee getById(final Object id) {
		return super.getById(HiredEmployee.class, id);
	}

	public HiredEmployee getByIdRef(final Object id) {
		return super.getByIdRef(HiredEmployee.class, id);
	}

	public HiredEmployee getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(HiredEmployee.class, id);
	}
	
	public List<HiredEmployee> getAll() {
		return super.getAll(HiredEmployee.class);
	}

	public HiredEmployee save(HiredEmployee data) {
		return super.save(data);
	}

	public HiredEmployee saveAndFlush(HiredEmployee entity) {
		return super.saveAndFlush(entity);
	}

	public HiredEmployee saveNoLogin(HiredEmployee entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(HiredEmployee.class, entityId);
	}

	public List<HiredEmployee> getByCompany(int firstIndex, int endIndex, String companyId) {
		final String sql = "SELECT "
				+ "	the.id, tcp.profile_name, tc2.company_name "
				+ "FROM "
				+ "	t_hired_employee the "
				+ "INNER JOIN "
				+ "	t_candidate tc ON the.candidate_id = tc.id "
				+ "INNER JOIN "
				+ "	t_candidate_profile tcp ON tc.candidate_profile_id = tcp.id "
				+ "INNER JOIN "
				+ "	t_company tc2 ON the.company_id = tc2.id "
				+ "WHERE "
				+ "	the.company_id = :companyId";
		
		final List<?> hirObjs = ConnHandler.getManager()
				.createNativeQuery(sql)
				.setParameter("companyId", companyId)
				.setFirstResult(firstIndex)
				.setMaxResults(endIndex)
				.getResultList();
		
		final List<HiredEmployee> hiredEmployees = new ArrayList<>();
		
		if (hirObjs.size() > 0) {
			for (Object hirObj:hirObjs) {
				final Object[] hirObjArr = (Object[]) hirObj;
				final HiredEmployee hiredEmployee = new HiredEmployee();
				hiredEmployee.setId(hirObjArr[0].toString());
				
				final Candidate candidate = new Candidate();
				
				final CandidateProfile candidateProfile = new CandidateProfile();
				candidateProfile.setProfileName(hirObjArr[1].toString());
				candidate.setCandidateProfile(candidateProfile);
				
				hiredEmployee.setCandidate(candidate);
				
				final Company company = new Company();
				company.setCompanyName(hirObjArr[2].toString());
				hiredEmployee.setCompany(company);
				
				hiredEmployees.add(hiredEmployee);
			}
		}
		
		return hiredEmployees;
	}
	
	public HiredEmployee getByCandidate(String companyId, String candidateId) {
		final String sql = "SELECT "
				+ "	the.id "
				+ "FROM "
				+ "	t_hired_employee the "
				+ "INNER JOIN "
				+ "	t_candidate tc ON the.candidate_id = tc.id "
				+ "WHERE "
				+ "	the.candidate_id = :candidateId AND the.company_id = :companyId";
		
		try {
			final Object hirObj = ConnHandler.getManager()
					.createNativeQuery(sql)
					.setParameter("candidateId", candidateId)
					.setParameter("companyId", companyId)
					.getSingleResult();

			HiredEmployee employee = null;
			if (hirObj != null) {
				final Object[] hirObjArr = (Object[]) hirObj;

				if(hirObjArr.length > 0) {
					employee = new HiredEmployee();
					employee.setId(hirObjArr[0].toString());
					
				}
			}
			return employee;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}