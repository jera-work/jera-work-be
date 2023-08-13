package com.lawencon.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public <T> String writeTo(String url, T data) {
		
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		final RequestEntity<T> request = RequestEntity.post(url).headers(headers).body(data);
		
		final ResponseEntity<String> response = restTemplate.exchange(request, String.class);
		
		return response.getBody();
	}

}
