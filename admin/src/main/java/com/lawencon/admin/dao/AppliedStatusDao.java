package com.lawencon.admin.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.admin.model.AppliedStatus;

@Repository
@Profile(value = { "native-query" })
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

}