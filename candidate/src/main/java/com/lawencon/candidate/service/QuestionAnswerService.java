package com.lawencon.candidate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.dao.CandidateDao;
import com.lawencon.candidate.dto.InsertResDto;
import com.lawencon.candidate.dto.questionanswer.InsertQuestionAnswerReqDto;
import com.lawencon.candidate.model.Candidate;
import com.lawencon.security.principal.PrincipalServiceImpl;

@Service
public class QuestionAnswerService {
	
	@Autowired
	private CandidateDao candidateDao;
	
	@Autowired
	private PrincipalServiceImpl principalService;
	
	@Autowired
	private EmailEncoderService emailEncoderService;
	
	@Autowired
	private ApiService apiService;
	
	public InsertResDto submitAnswer(List<InsertQuestionAnswerReqDto> data) {
		final InsertResDto response = new InsertResDto();
		
		if (data.size() > 0) {
			ConnHandler.begin();
			
			for(int i = 0; i < data.size(); i++) {
				final Candidate candidate = candidateDao.getById(principalService.getAuthPrincipal());
				final String email = emailEncoderService.encodeEmail(candidate.getCandidateEmail());
				data.get(i).setCandidateEmail(email);
			}
			
			apiService.writeTo("http://localhost:8081/answers", data);
			response.setMessage("Answer submitted successfully");
			ConnHandler.commit();
		}
		return response;
	}
}
