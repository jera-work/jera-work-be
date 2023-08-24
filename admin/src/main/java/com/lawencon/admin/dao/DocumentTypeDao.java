package com.lawencon.admin.dao;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.DocumentType;
import com.lawencon.base.AbstractJpaDao;

@Repository
public class DocumentTypeDao extends AbstractJpaDao {
	
	public DocumentType getById(final Object id) {
		return super.getById(DocumentType.class, id);
	}

	public DocumentType getByIdRef(final Object id) {
		return super.getByIdRef(DocumentType.class, id);
	}

	public DocumentType getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(DocumentType.class, id);
	}
	
	public List<DocumentType> getAll() {
		return super.getAll(DocumentType.class);
	}

	public DocumentType save(DocumentType data) {
		return super.save(data);
	}

	public DocumentType saveAndFlush(DocumentType entity) {
		return super.saveAndFlush(entity);
	}

	public DocumentType saveNoLogin(DocumentType entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(DocumentType.class, entityId);
	}

}