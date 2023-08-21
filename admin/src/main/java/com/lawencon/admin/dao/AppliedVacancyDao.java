package com.lawencon.admin.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.AppliedProgress;
import com.lawencon.admin.model.AppliedStatus;
import com.lawencon.admin.model.AppliedVacancy;
import com.lawencon.admin.model.Candidate;
import com.lawencon.admin.model.CandidateProfile;
import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

@Repository
public class AppliedVacancyDao extends AbstractJpaDao {
	
	public AppliedVacancy getById(final Object id) {
		return super.getById(AppliedVacancy.class, id);
	}

	public AppliedVacancy getByIdRef(final Object id) {
		return super.getByIdRef(AppliedVacancy.class, id);
	}

	public AppliedVacancy getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(AppliedVacancy.class, id);
	}
	
	public List<AppliedVacancy> getAll() {
		return super.getAll(AppliedVacancy.class);
	}

	public AppliedVacancy save(AppliedVacancy data) {
		return super.save(data);
	}

	public AppliedVacancy saveAndFlush(AppliedVacancy entity) {
		return super.saveAndFlush(entity);
	}

	public AppliedVacancy saveNoLogin(AppliedVacancy entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(AppliedVacancy.class, entityId);
	}
	
	public AppliedVacancy getByJobVacancyAndCandidate(String jobId, String candidateId){
		final String sql = "SELECT "
				+ "	tav.id "
				+ "FROM "
				+ "	t_applied_vacancy tav "
				+ "INNER JOIN "
				+ "	t_candidate tc ON tav.candidate_id = tc.id "
				+ "INNER JOIN "
				+ "	t_job_vacancy tjv ON tav.job_vacancy_id = tjv.id "
				+ "WHERE "
				+ "	tc.id LIKE :candidate_id AND tjv.id LIKE :job_id ";
		
		try {
			final Object appJobObj = ConnHandler.getManager().createNativeQuery(sql)
					.setParameter("job_id", jobId)
					.setParameter("candidate_id", candidateId)
					.getSingleResult();

				AppliedVacancy appliedVacancy = new AppliedVacancy();
				appliedVacancy.setId(appJobObj.toString());
			return appliedVacancy;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public AppliedVacancy getByAppliedVacancyId(String appliedVacancyId) {
		final String sql = "SELECT "
				+ "	tav.id, tcp.profile_name, tap.progress_name, tas.status_name "
				+ "FROM "
				+ "	t_applied_vacancy tav "
				+ "INNER JOIN "
				+ "	t_candidate tc ON tav.candidate_id = tc.id "
				+ "INNER JOIN "
				+ "	t_candidate_profile tcp ON tc.candidate_profile_id = tcp.id "
				+ "INNER JOIN "
				+ "	t_applied_progress tap ON tav.applied_progress_id = tap.id "
				+ "INNER JOIN "
				+ "	t_applied_status tas ON tav.applied_status_id = tas.id "
				+ "WHERE "
				+ "	tav.job_vacancy_id = :jobVacancyId";
		
		final Object appObj = ConnHandler.getManager()
				.createNativeQuery(sql)
				.setParameter("appliedVacancyId", appliedVacancyId)
				.getSingleResult();
		
		final Object[] appObjArr = (Object[]) appObj;
		
		try {
			final AppliedVacancy appliedVacancy = new AppliedVacancy();
			appliedVacancy.setId(appObjArr[0].toString());
			
			final Candidate candidate = new Candidate();
			
			final CandidateProfile candidateProfile = new CandidateProfile();
			candidateProfile.setProfileName(appObjArr[1].toString());
			candidate.setCandidateProfile(candidateProfile);
			appliedVacancy.setCandidate(candidate);
			
			final AppliedProgress appliedProgress = new AppliedProgress();
			appliedProgress.setProgressName(appObjArr[2].toString());
			appliedVacancy.setAppliedProgress(appliedProgress);
			
			final AppliedStatus appliedStatus = new AppliedStatus();
			appliedStatus.setStatusName(appObjArr[3].toString());
			appliedVacancy.setAppliedStatus(appliedStatus);
			
			return appliedVacancy;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}