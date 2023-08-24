package com.lawencon.candidate.service;

import java.util.Base64;

import org.springframework.stereotype.Service;

@Service
public class EmailEncoderService {
	
	public String encodeEmail(String email) {
		final String encoded = Base64.getUrlEncoder().encodeToString(email.getBytes());
		return encoded;
	}
	
	public String decodeEmail(String encodedEmail) {
		final byte[] decodeEmail = Base64.getUrlDecoder().decode(encodedEmail);
		final String email = new String(decodeEmail);
		return email;
	}

}
