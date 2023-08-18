package com.lawencon.admin.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.Candidate;
import com.lawencon.admin.model.CandidateProfile;
import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

@Repository
@Profile(value = { "native-query" })
public class CandidateDao extends AbstractJpaDao {

	public Candidate getById(final Object id) {
		return super.getById(Candidate.class, id);
	}

	public Candidate getByIdRef(final Object id) {
		return super.getByIdRef(Candidate.class, id);
	}

	public Candidate getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(Candidate.class, id);
	}

	public List<Candidate> getAll() {
		return super.getAll(Candidate.class);
	}

	public Candidate save(Candidate data) {
		return super.save(data);
	}

	public Candidate saveAndFlush(Candidate entity) {
		return super.saveAndFlush(entity);
	}

	public Candidate saveNoLogin(Candidate entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(Candidate.class, entityId);
	}

	public String getSystemId() {
		final String sql = "SELECT " + "tu.id " + "FROM " + "t_user tu " + "WHERE "
				+ "tu.user_email = 'system-adm@email.com' ";

		return (String) ConnHandler.getManager().createNativeQuery(sql).getSingleResult();
	}

	public Candidate getByEmail(String candidateEmail) {
		final String sql = 
				"SELECT " 
					+ "tc.id, tc.candidate_profile_id " 
				+ "FROM " 
					+ "t_candidate tc "
//				+ "INNER JOIN "
//					+ "t_candidate_profile tcp ON tcp.id = tc.candidate_profile_id"
				+ "WHERE "
					+ "tc.candidate_email = :email";

		try {
			
			final Object cdtObj = ConnHandler.getManager().createNativeQuery(sql)
					.setParameter("email", candidateEmail)
					.getSingleResult();
			
			final Object[] cdtArr = (Object[]) cdtObj;
			Candidate cdt = null;
			if(cdtArr.length > 0) {
				
				final CandidateProfile profile = new CandidateProfile();
				profile.setId(cdtArr[1].toString());
				
				cdt = new Candidate();
				cdt.setId(cdtArr[0].toString());
				cdt.setCandidateProfile(profile);
			}
			
			return cdt;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
