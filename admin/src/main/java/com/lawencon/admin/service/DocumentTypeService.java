package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.DocumentTypeDao;
import com.lawencon.admin.dto.documenttype.DocumentTypeResDto;

@Service
public class DocumentTypeService {

	@Autowired
	private DocumentTypeDao documentTypeDao;
	
	public List<DocumentTypeResDto> getAll() {
		final List<DocumentTypeResDto> responses = new ArrayList<>();
		
		documentTypeDao.getAll().forEach(av -> {
			final DocumentTypeResDto response = new DocumentTypeResDto();
			response.setId(av.getId());
			response.setTypeCode(av.getTypeCode());
			response.setTypeName(av.getTypeName());
			responses.add(response);
		});
		
		return responses;
	}
}
