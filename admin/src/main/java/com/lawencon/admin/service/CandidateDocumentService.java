package com.lawencon.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.CandidateDao;
import com.lawencon.admin.dao.CandidateDocumentDao;
import com.lawencon.admin.dao.DocumentTypeDao;
import com.lawencon.admin.dao.FileDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.document.CandidateDocumentCreateReqDto;
import com.lawencon.admin.model.Candidate;
import com.lawencon.admin.model.CandidateDocument;
import com.lawencon.admin.model.File;
import com.lawencon.base.ConnHandler;

@Service
public class CandidateDocumentService {
	
	@Autowired
	private CandidateDao candidateDao;
	@Autowired
	private FileDao fileDao;
	@Autowired
	private CandidateDocumentDao docsDao;
	@Autowired
	private DocumentTypeDao typeDao;
	
	/* Insert documents for candidate */
	public InsertResDto insertCandidateDocs(List<CandidateDocumentCreateReqDto> datas) {

		try {
			ConnHandler.begin();
			InsertResDto response = null;
			for (CandidateDocumentCreateReqDto data : datas) {
				final Candidate candidate = candidateDao.getByEmail(data.getCandidateEmail());
				final File file = new File();
				file.setFileContent(data.getFileContent());
				file.setFileExt(data.getFileExt());
				final File fileDb = fileDao.save(file);

				final CandidateDocument doc = new CandidateDocument();
				doc.setCandidate(candidate);
				doc.setDocumentType(typeDao.getByIdRef(data.getDocumentTypeId()));
				doc.setFile(fileDb);
				final CandidateDocument docDb = docsDao.save(doc);

				response = new InsertResDto();
				response.setId(docDb.getId());
				response.setMessage("Documents has been added!");
			}
			ConnHandler.commit();
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			ConnHandler.rollback();
			return null;
		}

	}

}
