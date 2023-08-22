package com.lawencon.candidate.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.candidate.model.Offering;

@Repository
@Profile(value = { "native-query" })
public class OfferingDao extends AbstractJpaDao {
	
	public Offering getById(final Object id) {
		return super.getById(Offering.class, id);
	}

	public Offering getByIdRef(final Object id) {
		return super.getByIdRef(Offering.class, id);
	}

	public Offering getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(Offering.class, id);
	}
	
	public List<Offering> getAll() {
		return super.getAll(Offering.class);
	}

	public Offering save(Offering data) {
		return super.save(data);
	}

	public Offering saveAndFlush(Offering entity) {
		return super.saveAndFlush(entity);
	}

	public Offering saveNoLogin(Offering entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(Offering.class, entityId);
	}

}
