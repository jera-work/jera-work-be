package com.lawencon.candidate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.dao.CandidateDao;
import com.lawencon.candidate.dao.CandidateDocumentDao;
import com.lawencon.candidate.dao.FileDao;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.document.CandidateDocumentCreateReqDto;
import com.lawencon.candidate.model.Candidate;
import com.lawencon.candidate.model.CandidateDocument;
import com.lawencon.candidate.model.File;
import com.lawencon.security.principal.PrincipalServiceImpl;

@Service
public class CandidateDocumentService {
	
	@Autowired
	private CandidateDao candidateDao;
	@Autowired
	private PrincipalServiceImpl principalService;
	@Autowired
	private FileDao fileDao;
	@Autowired
	private CandidateDocumentDao docsDao;
	@Autowired
	private ApiService apiService;
	
	/* Insert documents for candidate */
	public InsertResDto insertCandidateDocs(List<CandidateDocumentCreateReqDto> datas) {
		final Candidate candidate = candidateDao.getByIdRef(principalService.getAuthPrincipal());

		try {
			ConnHandler.begin();
			InsertResDto response = null;
			for (CandidateDocumentCreateReqDto data : datas) {
				final File file = new File();
				file.setFileContent(data.getFileContent());
				file.setFileExt(data.getFileExt());
				final File fileDb = fileDao.save(file);

				final CandidateDocument doc = new CandidateDocument();
				doc.setCandidate(candidate);
				doc.setDocumentType(data.getDocumentTypeId());
				doc.setFile(fileDb);
				final CandidateDocument docDb = docsDao.save(doc);

				data.setCandidateEmail(candidate.getCandidateEmail());

				response = new InsertResDto();
				response.setId(docDb.getId());
				response.setMessage("Documents has been added!");
			}

			apiService.writeTo("http://localhost:8081/candidates/documents", datas);
			ConnHandler.commit();
			return response;
		} catch (Exception e) {
			ConnHandler.rollback();
			return null;
		}

	}

}
