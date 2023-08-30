package com.lawencon.candidate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.dao.CandidateDao;
import com.lawencon.candidate.dao.CandidateDocumentDao;
import com.lawencon.candidate.dao.FileDao;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.document.CandidateDocumentCreateReqDto;
import com.lawencon.candidate.dto.document.CandidateDocumentResDto;
import com.lawencon.candidate.model.Candidate;
import com.lawencon.candidate.model.CandidateDocument;
import com.lawencon.candidate.model.File;
import com.lawencon.candidate.util.GenerateUtil;
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
	@Autowired
	private EmailEncoderService encoderService;
	
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
				doc.setDocumentCode(GenerateUtil.generateRandomCode());
				doc.setFile(fileDb);
				final CandidateDocument docDb = docsDao.save(doc);

				data.setCandidateEmail(candidate.getCandidateEmail());
				data.setDocumentCode(docDb.getDocumentCode());
				response = new InsertResDto();
				response.setId(docDb.getId());
				response.setMessage("Documents has been added!");
			}

			apiService.writeTo("http://localhost:8081/documents", datas);
			ConnHandler.commit();
			return response;
		} catch (Exception e) {
			ConnHandler.rollback();
			return null;
		}

	}
	
	/* get documents for candidate */
	public List<CandidateDocumentResDto> getDocuments() {
		final Candidate candidate = candidateDao.getById(principalService.getAuthPrincipal());
		final String email = encoderService.encodeEmail(candidate.getCandidateEmail());
		final List<CandidateDocument> documents = docsDao.getDocuments(candidate.getId());
		final String url = "http://localhost:8081/documents/?email=" + email;
		
		final List<CandidateDocumentResDto> responseFromAdmin = apiService.getListFrom(url, CandidateDocumentResDto.class);
		final List<CandidateDocumentResDto> responses = new ObjectMapper().convertValue(responseFromAdmin, new TypeReference<List<CandidateDocumentResDto>>() {});
		
		for (int i = 0; i < responses.size(); i++) {
			responses.get(i).setFileId(documents.get(i).getFile().getId());
			responses.get(i).setId(documents.get(i).getId());
		}
		return responses;
	}

}
