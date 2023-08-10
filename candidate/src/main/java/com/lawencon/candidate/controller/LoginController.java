//package com.lawencon.candidate.controller;
//
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.lawencon.candidate.login.LoginReqDto;
//import com.lawencon.candidate.login.LoginResDto;
//import com.lawencon.candidate.model.Candidate;
//import com.lawencon.candidate.service.CandidateService;
//import com.lawencon.tokenmanager.service.JwtService;
//
//@RestController
//@RequestMapping("login")
//public class LoginController {
//
//	@Autowired
//	private CandidateService cdtService;
//	
//	@Autowired
//	private AuthenticationManager authenticationManager;
//	
//	@Autowired
//	private JwtService jwtService;
//
//	@PostMapping
//	public ResponseEntity<?> login(@RequestBody final LoginReqDto data) {
//		final Authentication auth = new UsernamePasswordAuthenticationToken(data.getUserEmail(), data.getUserPass());
//
//		authenticationManager.authenticate(auth);
//		final Candidate cdt = cdtService.login(data);
//
//		final Calendar cal = Calendar.getInstance();
//		cal.setTime(new Date());
//		cal.add(Calendar.HOUR_OF_DAY, 1);
//
//		final Map<String, Object> claims = new HashMap<>();
//		claims.put("exp", cal.getTime());
//		claims.put("id", cdt.getId());
//
//		final LoginResDto loginRes = new LoginResDto();
//		loginRes.setId(cdt.getId());
//		loginRes.setToken(jwtService.generateJwt(claims));
//		loginRes.setProfileName(cdt.getCandidateProfile().getProfileName());
//		loginRes.setPhotoId(cdt.getCandidateProfile().getPhoto().getId());
//
//		return new ResponseEntity<>(loginRes, HttpStatus.OK);
//	}
//}
