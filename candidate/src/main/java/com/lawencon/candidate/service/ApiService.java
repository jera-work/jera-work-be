package com.lawencon.candidate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lawencon.config.JwtConfig;

@Service
public class ApiService {

	@Autowired
	private RestTemplate restTemplate;

	public <T> HttpStatus writeTo(String url, T data) {

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(JwtConfig.get());

		final RequestEntity<T> request = RequestEntity.post(url).headers(headers).body(data);

		final ResponseEntity<String> response = restTemplate.exchange(request, String.class);

		return response.getStatusCode();
	}

	public <T> HttpStatus putTo(String url, T data) {

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(JwtConfig.get());

		final RequestEntity<T> request = RequestEntity.put(url).headers(headers).body(data);

		final ResponseEntity<String> response = restTemplate.exchange(request, String.class);

		return response.getStatusCode();
	}
	
	public <T> T getFrom(String url, Class<T> type) {

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(JwtConfig.get());

		final RequestEntity<Void> request = RequestEntity.get(url).headers(headers).build();

		final ResponseEntity<T> response = restTemplate.exchange(request, type);

		return response.getBody();
	}
	
	public <T> List<T> getListFrom(String url, Class<T> type) {

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(JwtConfig.get());

		final RequestEntity<Void> request = RequestEntity.get(url).headers(headers).build();

		final ResponseEntity<List<T>> response = restTemplate.exchange(request, new ParameterizedTypeReference<List<T>>(){});
		final List<T> result = response.getBody();
		
		return result;
	}
	
	public <T> T delete(String url, Class<T> type) {
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(JwtConfig.get());
		
		final RequestEntity<Void> request = RequestEntity.delete(url).headers(headers).build();
		final ResponseEntity<T> response = restTemplate.exchange(request, type);
		
		return response.getBody(); 
	}
}
