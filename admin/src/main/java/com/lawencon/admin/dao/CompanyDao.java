package com.lawencon.admin.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.admin.model.Company;

@Repository
@Profile(value = { "native-query" })
public class CompanyDao extends AbstractJpaDao {
	
	public Company getById(final Object id) {
		return super.getById(Company.class, id);
	}

	public Company getByIdRef(final Object id) {
		return super.getByIdRef(Company.class, id);
	}

	public Company getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(Company.class, id);
	}
	
	public List<Company> getAll() {
		return super.getAll(Company.class);
	}

	public Company save(Company data) {
		return super.save(data);
	}

	public Company saveAndFlush(Company entity) {
		return super.saveAndFlush(entity);
	}

	public Company saveNoLogin(Company entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(Company.class, entityId);
	}

}