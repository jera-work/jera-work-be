package com.lawencon.admin.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.admin.model.City;

@Repository
@Profile(value = { "native-query" })
public class CityDao extends AbstractJpaDao {
	
	public City getById(final Object id) {
		return super.getById(City.class, id);
	}

	public City getByIdRef(final Object id) {
		return super.getByIdRef(City.class, id);
	}

	public City getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(City.class, id);
	}
	
	public List<City> getAll() {
		return super.getAll(City.class);
	}

	public City save(City data) {
		return super.save(data);
	}

	public City saveAndFlush(City entity) {
		return super.saveAndFlush(entity);
	}

	public City saveNoLogin(City entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(City.class, entityId);
	}

}