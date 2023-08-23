package com.lawencon.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.CompanyDao;
import com.lawencon.admin.dao.ProfileDao;
import com.lawencon.admin.dao.RoleDao;
import com.lawencon.admin.dao.UserDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.login.LoginReqDto;
import com.lawencon.admin.dto.user.UserCreateReqDto;
import com.lawencon.admin.dto.user.UserResDto;
import com.lawencon.admin.model.Company;
import com.lawencon.admin.model.Profile;
import com.lawencon.admin.model.Role;
import com.lawencon.admin.model.User;
import com.lawencon.base.ConnHandler;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private ProfileDao profileDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private SendMailService sendMailService;

	public InsertResDto createUser(UserCreateReqDto data) {
		ConnHandler.begin();
		final char[] ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

		final StringBuilder randomStr = new StringBuilder();
		String generatedString = "";

		for (int i = 0; i < 5; i++) {
			final int index = (int) (Math.random() * ALPHANUMERIC.length);
			randomStr.append(ALPHANUMERIC[index]);
		}

		generatedString = randomStr.toString();

		final String passwordEncoded = passwordEncoder.encode(generatedString);
		final User user = new User();
		user.setUserEmail(data.getUserEmail());
		user.setUserPassword(passwordEncoded);

		final Profile profile = new Profile();
		profile.setProfileName(data.getProfileName());
		
		final Company company = companyDao.getByIdRef(data.getCompanyId());
		profile.setCompany(company);
		final Profile profileDb = profileDao.save(profile);

		user.setProfile(profileDb);

		final Role role = roleDao.getByIdRef(data.getRoleId());
		user.setRole(role);
		final User userDb = userDao.saveAndFlush(user);
		
		ConnHandler.commit();

		sendMailService.sendEmail(userDb.getUserEmail(), "Account created successfully", "Hello, "
				+ data.getProfileName()
				+ "! Your account has been created successfully for jera-work app, you can login using this password : " + generatedString);
		final InsertResDto response = new InsertResDto();
		response.setId(userDb.getId());
		response.setMessage("User created successfully");

		return response;
	}
	
	public List<UserResDto> getAllUsers() {
		final List<User> users = userDao.getAll();
		final List<UserResDto> responses = new ArrayList<>();

		users.forEach(user -> {
			final UserResDto response = new UserResDto();
			response.setId(user.getId());
			response.setProfileName(user.getProfile().getProfileName());
			response.setCompanyId(user.getProfile().getCompany().getId());
			response.setCompanyName(user.getProfile().getCompany().getCompanyName());
			response.setRoleName(user.getRole().getRoleName());
			responses.add(response);
		});

		return responses;
	}

	public List<UserResDto> getUsers(String roleCode, String companyCode) {
		final List<User> users = userDao.getByRoleCode(roleCode, companyCode);
		final List<UserResDto> responses = new ArrayList<>();

		users.forEach(user -> {
			final UserResDto response = new UserResDto();
			response.setId(user.getId());
			response.setProfileName(user.getProfile().getProfileName());
			responses.add(response);
		});

		return responses;
	}

	public User login(LoginReqDto data) {
		final String userEmail = data.getUserEmail();
		final User userLogin = userDao.getByEmail(userEmail);
		return userLogin;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final User detail = userDao.getByEmail(username);

		if (detail != null) {
			return new org.springframework.security.core.userdetails.User(username, detail.getUserPassword(),
					new ArrayList<>());
		}

		throw new UsernameNotFoundException("User not found");
	}

}
