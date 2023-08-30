package com.lawencon.candidate.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.model.Candidate;
import com.lawencon.candidate.model.CandidateProfile;
import com.lawencon.candidate.model.File;

@Repository
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
	
	public Candidate getByEmail(String email) {
		final String sql = "SELECT "
					+ "tc.id, tc.candidate_password, tcp.profile_name , tf.id as fileId, tc.candidate_code "
					+ "FROM "
					+ "t_candidate tc "
					+ "INNER JOIN "
					+ "t_candidate_profile tcp ON tc.candidate_profile_id = tcp.id "
					+ "LEFT JOIN "
					+ "t_file tf ON tcp.photo_id = tf.id "
					+ "WHERE "
					+ "tc.candidate_email = :email ";
		try {
			final Object cdtObj = ConnHandler.getManager()
					.createNativeQuery(sql)
					.setParameter("email", email)
					.getSingleResult();
			
			final Object[] cdtArr = (Object[]) cdtObj;
			Candidate cdt = null;
			if(cdtArr.length > 0) {
				cdt = new Candidate();
				cdt.setId(cdtArr[0].toString());
				cdt.setCandidatePassword(cdtArr[1].toString());
				cdt.setCandidateCode(cdtArr[4].toString());
				
				final CandidateProfile profile = new CandidateProfile();
				profile.setProfileName(cdtArr[2].toString());
				
				if(cdtArr[3] != null) {
					final File photo = new File();
					photo.setId(cdtArr[3].toString());
					profile.setPhoto(photo);					
				}
				
				cdt.setCandidateProfile(profile);
			}
			return cdt;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getSystemId() {
		final String sql = "SELECT "
				+ "tc.id "
				+ "FROM "
				+ "t_candidate tc "
				+ "WHERE "
				+ "tc.candidate_email = 'system-cdt@email.com' ";
		
		return (String) ConnHandler.getManager().createNativeQuery(sql).getSingleResult();
	}
}
