package com.lawencon.candidate.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.model.SavedJobs;

@Repository
public class SavedJobsDao extends AbstractJpaDao {
	
	public SavedJobs getById(final Object id) {
		return super.getById(SavedJobs.class, id);
	}

	public SavedJobs getByIdRef(final Object id) {
		return super.getByIdRef(SavedJobs.class, id);
	}

	public SavedJobs getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(SavedJobs.class, id);
	}
	
	public List<SavedJobs> getAll() {
		return super.getAll(SavedJobs.class);
	}

	public SavedJobs save(SavedJobs data) {
		return super.save(data);
	}

	public SavedJobs saveAndFlush(SavedJobs entity) {
		return super.saveAndFlush(entity);
	}

	public SavedJobs saveNoLogin(SavedJobs entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(SavedJobs.class, entityId);
	}

	public SavedJobs getbyJobAndCandidate(String jobId, String candidateId) {
		final String sql = "SELECT "
				+ "	tsj.id "
				+ "FROM "
				+ "	t_saved_jobs tsj "
				+ "INNER JOIN "
				+ "	t_candidate tc ON tsj.candidate_id = tc.id "
				+ "INNER JOIN "
				+ "	t_job_vacancy tjv ON tsj.job_vacancy_id = tjv.id "
				+ "WHERE "
				+ "	tsj.candidate_id = :candidateId AND tsj.job_vacancy_id = :jobId";
		
		try {
			final Object savObj = ConnHandler.getManager()
					.createNativeQuery(sql)
					.setParameter("candidateId", candidateId)
					.setParameter("jobId", jobId)
					.getSingleResult();
			
			final SavedJobs jobs = new SavedJobs();
			jobs.setId(savObj.toString());
			
			return jobs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}