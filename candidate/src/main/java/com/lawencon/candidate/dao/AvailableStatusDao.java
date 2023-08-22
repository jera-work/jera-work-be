package com.lawencon.candidate.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.candidate.model.AvailableStatus;

@Repository
@Profile(value = { "native-query" })
public class AvailableStatusDao extends AbstractJpaDao {
	
	public AvailableStatus getById(final Object id) {
		return super.getById(AvailableStatus.class, id);
	}

	public AvailableStatus getByIdRef(final Object id) {
		return super.getByIdRef(AvailableStatus.class, id);
	}

	public AvailableStatus getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(AvailableStatus.class, id);
	}
	
	public List<AvailableStatus> getAll() {
		return super.getAll(AvailableStatus.class);
	}

	public AvailableStatus save(AvailableStatus data) {
		return super.save(data);
	}

	public AvailableStatus saveAndFlush(AvailableStatus entity) {
		return super.saveAndFlush(entity);
	}

	public AvailableStatus saveNoLogin(AvailableStatus entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(AvailableStatus.class, entityId);
	}

}