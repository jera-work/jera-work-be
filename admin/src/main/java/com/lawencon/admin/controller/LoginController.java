//package com.lawencon.admin.controller;
//
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.validation.Valid;
//
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
//import com.lawencon.admin.dto.ErrorResDto;
//import com.lawencon.admin.dto.login.LoginResDto;
//import com.lawencon.admin.service.UserService;
//
//import io.swagger.v3.oas.annotations.security.SecurityRequirement;
//
//@SecurityRequirement(name = "bearerAuth")
//@RestController
//@RequestMapping("login")
//public class LoginController {
//
//	private final UserService userService;
//	private final AuthenticationManager authenticationManager;
//	private final JwtService jwtService;
//
//	public LoginController(UserService userService, AuthenticationManager authenticationManager,
//			JwtService jwtService, CandidateAssignService candidateAssignService) {
//		this.candidateAssignService = candidateAssignService;
//		this.userService = userService;
//		this.authenticationManager = authenticationManager;
//		this.jwtService = jwtService;
//	}
//
//	@PostMapping
//	public ResponseEntity<?> login(@Valid @RequestBody final LoginReqDto user) {
//		final Authentication auth = new UsernamePasswordAuthenticationToken(user.getUserEmail(), user.getUserPwd());
//		authenticationManager.authenticate(auth);
//		final LoginResDto userOptional = userService.login(user);
//		if (userOptional != null) {
//			if(UserRole.CANDIDATE.roleCode.equals(userOptional.getRoleCode())) {
//				final List<CandidateAssignResDto> candidateAssign = candidateAssignService.getCandidateAssign(userOptional.getId());
//				if(candidateAssign.isEmpty()) {
//					System.err.println(candidateAssign);
//					final ErrorResDto<String> errorRes = new ErrorResDto<>();
//					errorRes.setMessage("Account Inactive");
//					return new ResponseEntity<>(errorRes, HttpStatus.UNAUTHORIZED);
//				}
//			}
//			final Calendar cal = Calendar.getInstance();
//			cal.setTime(new Date());
//			cal.add(Calendar.HOUR_OF_DAY, 1);
//
//			final Map<String, Object> claims = new HashMap<>();
//			claims.put("exp", cal.getTime());
//			claims.put("id", userOptional.getId());
//
//			final LoginResDto loginRes = new LoginResDto();
//			loginRes.setId(userOptional.getId());
//			loginRes.setToken(jwtService.generateJwt(claims));
//			loginRes.setFullName(userOptional.getFullName());
//			loginRes.setAddress(userOptional.getAddress());
//			loginRes.setRoleName(userOptional.getRoleName());
//			loginRes.setRoleId(userOptional.getRoleId());
//			loginRes.setRoleCode(userOptional.getRoleCode());
//			loginRes.setPhotoFileId(userOptional.getPhotoFileId());
//			return new ResponseEntity<>(loginRes, HttpStatus.OK);
//		}else {
//			final ErrorResDto<String> errorRes = new ErrorResDto<>();
//			errorRes.setMessage("Account Inactive");
//			return new ResponseEntity<>(errorRes, HttpStatus.UNAUTHORIZED);
//		}
//	}
