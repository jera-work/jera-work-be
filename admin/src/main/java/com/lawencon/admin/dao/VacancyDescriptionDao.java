package com.lawencon.admin.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.VacancyDescription;
import com.lawencon.base.AbstractJpaDao;

@Repository
public class VacancyDescriptionDao extends AbstractJpaDao {
	
	public VacancyDescription getById(final Object id) {
		return super.getById(VacancyDescription.class, id);
	}

	public VacancyDescription getByIdRef(final Object id) {
		return super.getByIdRef(VacancyDescription.class, id);
	}

	public VacancyDescription getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(VacancyDescription.class, id);
	}
	
	public List<VacancyDescription> getAll() {
		return super.getAll(VacancyDescription.class);
	}

	public VacancyDescription save(VacancyDescription data) {
		return super.save(data);
	}

	public VacancyDescription saveAndFlush(VacancyDescription entity) {
		return super.saveAndFlush(entity);
	}

	public VacancyDescription saveNoLogin(VacancyDescription entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(VacancyDescription.class, entityId);
	}

}
