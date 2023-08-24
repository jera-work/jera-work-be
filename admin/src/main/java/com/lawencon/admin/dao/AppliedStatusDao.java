package com.lawencon.admin.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.AppliedProgress;
import com.lawencon.admin.model.AppliedStatus;
import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

@Repository
public class AppliedStatusDao extends AbstractJpaDao {

	public AppliedStatus getById(final Object id) {
		return super.getById(AppliedStatus.class, id);
	}

	public AppliedStatus getByIdRef(final Object id) {
		return super.getByIdRef(AppliedStatus.class, id);
	}

	public AppliedStatus getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(AppliedStatus.class, id);
	}

	public List<AppliedStatus> getAll() {
		return super.getAll(AppliedStatus.class);
	}

	public AppliedStatus save(AppliedStatus data) {
		return super.save(data);
	}

	public AppliedStatus saveAndFlush(AppliedStatus entity) {
		return super.saveAndFlush(entity);
	}

	public AppliedStatus saveNoLogin(AppliedStatus entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(AppliedStatus.class, entityId);
	}
	public AppliedStatus getByCode(final String code) {
		final String sql = "SELECT "
				+ "tas.id "
				+ "FROM "
				+ "t_applied_status tas "
				+ "WHERE "
				+ "tas.status_code LIKE :code";
		
		final Object appObj = ConnHandler.getManager()
				.createNativeQuery(sql)
				.setParameter("code", code)
				.getSingleResult();
		
		final AppliedStatus appliedStatus = new AppliedStatus();
		appliedStatus.setId(appObj.toString());
		
		return appliedStatus;
	}
}