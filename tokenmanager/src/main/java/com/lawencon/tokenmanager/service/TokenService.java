package com.lawencon.tokenmanager.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.tokenmanager.dto.TokenReqDto;
import com.lawencon.tokenmanager.dto.TokenResDto;

@Service
public class TokenService {
	
	@Autowired
	private JwtService jwtService;

	public TokenResDto generateToken(TokenReqDto data) {
		
		final Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY, 1);

		final Map<String, Object> claims = new HashMap<>();
		claims.put("exp", cal.getTime());
		claims.put("id", data.getId());
		
		final String token = jwtService.generateJwt(claims);
		
		final TokenResDto response = new TokenResDto();
		response.setToken(token);
		
		return response;
		
	}

}
