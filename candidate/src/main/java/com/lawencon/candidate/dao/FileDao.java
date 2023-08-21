package com.lawencon.candidate.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.candidate.model.File;

@Repository
public class FileDao extends AbstractJpaDao {
	
	public File getById(final Object id) {
		return super.getById(File.class, id);
	}

	public File getByIdRef(final Object id) {
		return super.getByIdRef(File.class, id);
	}

	public File getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(File.class, id);
	}
	
	public List<File> getAll() {
		return super.getAll(File.class);
	}

	public File save(File data) {
		return super.save(data);
	}

	public File saveAndFlush(File entity) {
		return super.saveAndFlush(entity);
	}

	public File saveNoLogin(File entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(File.class, entityId);
	}

}