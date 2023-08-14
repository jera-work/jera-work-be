package com.lawencon.candidate.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.model.AppliedProgress;

@Repository
@Profile(value = { "native-query" })
public class AppliedProgressDao extends AbstractJpaDao {
	
	public AppliedProgress getById(final Object id) {
		return super.getById(AppliedProgress.class, id);
	}

	public AppliedProgress getByIdRef(final Object id) {
		return super.getByIdRef(AppliedProgress.class, id);
	}

	public AppliedProgress getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(AppliedProgress.class, id);
	}
	
	public List<AppliedProgress> getAll() {
		return super.getAll(AppliedProgress.class);
	}

	public AppliedProgress save(AppliedProgress data) {
		return super.save(data);
	}

	public AppliedProgress saveAndFlush(AppliedProgress entity) {
		return super.saveAndFlush(entity);
	}

	public AppliedProgress saveNoLogin(AppliedProgress entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(AppliedProgress.class, entityId);
	}
	
	public AppliedProgress getByCode(String progressCode)  {
		final String sql = "SELECT "
				+ "ap "
				+ "FROM "
				+ "AppliedProgress ap "
				+ "WHERE ap.progressCode = :progressCode";
		
		final AppliedProgress result = ConnHandler.getManager().createQuery(sql, AppliedProgress.class)
				.setParameter("progressCode", progressCode)
				.getSingleResult();
		
		return result;
	}
}