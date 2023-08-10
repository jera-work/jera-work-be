package com.lawencon.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lawencon.admin.dao.ProfileDao;
import com.lawencon.admin.dao.UserDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.register.RegisterReqDto;
import com.lawencon.admin.model.Profile;
import com.lawencon.admin.model.User;

public class UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private ProfileDao profileDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public InsertResDto register(RegisterReqDto data) {
		final String passwordEncoded = passwordEncoder.encode(data.getUserPassword());
		final User user = new User();
		user.setUserEmail(data.getUserEmail());
		user.setUserPassword(passwordEncoded);
		
		final Profile profile = new Profile();
		profile.setProfileName(data.getProfileName());
		final Profile profileDb = profileDao.saveAndFlush(profile);
		
		user.setProfile(profileDb);
		final User userDb = userDao.saveAndFlush(user);
		
		final InsertResDto response = new InsertResDto();
		response.setId(userDb.getId());
		response.setMessage("Your profile : " + userDb.getProfile().getProfileName() + " has been created!");
		
		return response;
	}
	
}
