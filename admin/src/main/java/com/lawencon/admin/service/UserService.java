package com.lawencon.admin.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.CompanyDao;
import com.lawencon.admin.dao.FileDao;
import com.lawencon.admin.dao.ProfileDao;
import com.lawencon.admin.dao.RoleDao;
import com.lawencon.admin.dao.UserDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.UpdateResDto;
import com.lawencon.admin.dto.email.EmailReqDto;
import com.lawencon.admin.dto.email.ReportReqDto;
import com.lawencon.admin.dto.login.LoginReqDto;
import com.lawencon.admin.dto.profile.ProfileUpdateReqDto;
import com.lawencon.admin.dto.role.RoleCountResDto;
import com.lawencon.admin.dto.user.UserChangePasswordReqDto;
import com.lawencon.admin.dto.user.UserCreateReqDto;
import com.lawencon.admin.dto.user.UserProfileResDto;
import com.lawencon.admin.dto.user.UserResDto;
import com.lawencon.admin.model.Company;
import com.lawencon.admin.model.File;
import com.lawencon.admin.model.Profile;
import com.lawencon.admin.model.Role;
import com.lawencon.admin.model.User;
import com.lawencon.admin.util.DateUtil;
import com.lawencon.base.ConnHandler;
import com.lawencon.security.principal.PrincipalServiceImpl;
import com.lawencon.util.JasperUtil;

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
	@Autowired
	private JasperUtil jasperUtil;
	@Autowired
	private PrincipalServiceImpl principalService;
	@Autowired
	private FileDao fileDao;

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
				+ "! Your account has been created successfully for jera-work app, you can login using this password : "
				+ generatedString);
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
			response.setPhoneNumber(user.getProfile().getProfilePhone());
			response.setProfileAddress(user.getProfile().getProfileAddress());
			responses.add(response);
		});

		return responses;
	}

	public List<UserResDto> getUsers(String roleCode) {
		final User userPrincipal = userDao.getById(principalService.getAuthPrincipal());
		final List<User> users = userDao.getByRoleCode(roleCode, userPrincipal.getProfile().getCompany().getId());
		final List<UserResDto> responses = new ArrayList<>();

		users.forEach(user -> {
			final UserResDto response = new UserResDto();
			response.setId(user.getId());
			response.setProfileName(user.getProfile().getProfileName());
			responses.add(response);
		});

		return responses;
	}
	
	public List<UserResDto> getByCompany(){
		final List<UserResDto> responses = new ArrayList<>();
		final User userPrincipal = userDao.getById(principalService.getAuthPrincipal());
		final Profile profile = profileDao.getById(userPrincipal.getProfile().getId());
		final Company company = companyDao.getById(profile.getCompany().getId());
		final List<User> users = userDao.getByCompany(company.getId());
		
		users.forEach(user -> {
			final UserResDto response = new UserResDto();
			response.setId(user.getId());
			response.setProfileName(user.getProfile().getProfileName());
			response.setCompanyId(user.getProfile().getCompany().getId());
			response.setCompanyName(user.getProfile().getCompany().getCompanyName());
			response.setRoleName(user.getRole().getRoleName());
			response.setPhoneNumber(user.getProfile().getProfilePhone());
			response.setProfileAddress(user.getProfile().getProfileAddress());
			if(user.getProfile().getPhoto() != null) {
				response.setPhotoId(user.getProfile().getPhoto().getId());				
			}
			responses.add(response);
		});
		
		return responses;
	}

	public UpdateResDto changePassword(UserChangePasswordReqDto data) {
		ConnHandler.begin();
		final String id = principalService.getAuthPrincipal();
		final User user = userDao.getById(id);
		if (user != null) {
			if (passwordEncoder.matches(data.getOldPassword(), user.getUserPassword())) {
				user.setUserPassword(passwordEncoder.encode(data.getNewPassword()));
				final User userDb = userDao.saveAndFlush(user);
				final UpdateResDto response = new UpdateResDto();
//				ConnHandler.getManager().flush();
				response.setMessage("Update Password Berhasil");
				response.setVer(userDb.getVersion());
				ConnHandler.commit();
				return response;
			}
		}
		throw new UsernameNotFoundException("Email / password salah");

	}

	public UserProfileResDto getProfile() {

		final User user = userDao.getById(principalService.getAuthPrincipal());

		final UserProfileResDto response = new UserProfileResDto();
		response.setUserId(user.getId());
		response.setUserEmail(user.getUserEmail());
		response.setProfileName(user.getProfile().getProfileName());
		response.setRoleName(user.getRole().getRoleName());
		response.setProfileAddress(user.getProfile().getProfileAddress());
		response.setPhoneNumber(user.getProfile().getProfilePhone());
		response.setCompanyName(user.getProfile().getCompany().getCompanyName());
		if(user.getProfile().getPhoto() != null) {
			response.setPhotoId(user.getProfile().getPhoto().getId());			
		}
		return response;
	}

	public UpdateResDto updateProfile(ProfileUpdateReqDto data) {
		UpdateResDto response = new UpdateResDto();

		try {
			ConnHandler.begin();
			final User user = userDao.getById(principalService.getAuthPrincipal());
			user.setUserEmail(data.getUserEmail());

			final Profile profile = profileDao.getById(user.getProfile().getId());
			profile.setProfileName(data.getProfileName());
			profile.setProfileAddress(data.getProfileAddress());
			profile.setProfilePhone(data.getPhoneNumber());

			File fileDb = new File();
			if (profile.getPhoto() != null) {
				final String oldPhotoId = profile.getPhoto().getId();
				if (data.getFileContent() != null && data.getFileContent() != "") {
					final File newPhoto = new File();
					newPhoto.setFileContent(data.getFileContent());
					newPhoto.setFileExt(data.getFileExt());
					fileDb = fileDao.saveAndFlush(newPhoto);
					profile.setPhoto(fileDb);
					fileDao.deleteById(oldPhotoId);
				}
			} else {
				if (data.getFileContent() != null && data.getFileContent() != "") {
					final File newPhoto = new File();
					newPhoto.setFileContent(data.getFileContent());
					newPhoto.setFileExt(data.getFileExt());
					fileDb = fileDao.saveAndFlush(newPhoto);
					profile.setPhoto(fileDb);
				}
			}

			final Profile profileDb = profileDao.saveAndFlush(profile);
			ConnHandler.commit();

			response.setMessage("Profile has been updated!");
			response.setVer(profileDb.getVersion());
		} catch (Exception e) {
			e.printStackTrace();
			ConnHandler.rollback();
		}

		return response;
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
	
	public InsertResDto getReport() {
		final User userLogin = userDao.getById(principalService.getAuthPrincipal());
		final Company userCompany = companyDao.getById(userLogin.getProfile().getCompany().getId());
		final List<User> users = userDao.getAll();
		final List<UserResDto> usersRes = new ArrayList<>();
		final InsertResDto response = new InsertResDto();
		
		users.forEach(user -> {
			final UserResDto userRes = new UserResDto();
			userRes.setId(user.getId());
			userRes.setProfileName(user.getProfile().getProfileName());
			userRes.setCompanyId(user.getProfile().getCompany().getId());
			userRes.setCompanyName(user.getProfile().getCompany().getCompanyName());
			userRes.setRoleName(user.getRole().getRoleName());
			userRes.setPhoneNumber(user.getProfile().getProfilePhone());
			userRes.setProfileAddress(user.getProfile().getProfileAddress());
			usersRes.add(userRes);
		});
		
		final List<RoleCountResDto> rolesCount = new ArrayList<>();
		for(int i = 0; i < usersRes.size(); i++) {
			final RoleCountResDto roleCount = new RoleCountResDto();
			roleCount.setRoleName(usersRes.get(i).getRoleName());
			roleCount.setRoleCount(1);
			
			boolean roleExist = false;
			
			for(RoleCountResDto r : rolesCount) {
				if(r.getRoleName().equals(usersRes.get(i).getRoleName())) {
					roleExist = true;
					break;
				}
			}
			
			if(!roleExist) {
				rolesCount.add(roleCount);
			}else {
				for(RoleCountResDto r : rolesCount) {
					if(r.getRoleName().equals(roleCount.getRoleName())) {
						roleCount.setRoleCount(r.getRoleCount()+1);
						rolesCount.set(i, roleCount);
					}
				}
			}
		}
		final Collection<List<UserResDto>> result = new ArrayList<>();
		result.add(usersRes);
		
		final Map<String, Object> parameters = new HashMap<>();
		parameters.put("usersList", usersRes);
		parameters.put("rolesList", rolesCount);
		
		try {				
        	byte[] dataOut = jasperUtil.responseToByteArray(result, parameters, "jasper-users");
                	
	        final EmailReqDto emailReqDto = new EmailReqDto();
			emailReqDto.setSubject("Users Report");
			emailReqDto.setEmail(userLogin.getUserEmail());
			
			final ReportReqDto reportReqDto = new ReportReqDto();
			reportReqDto.setHeader("Users List Report");
			reportReqDto.setFullName(userLogin.getProfile().getProfileName());
			reportReqDto.setCompanyName(userCompany.getCompanyName());
			reportReqDto.setCreatedAt(DateUtil.dateTimeFormat(LocalDateTime.now()));
			
			sendMailService.sendUsersReport(emailReqDto, reportReqDto, dataOut);
			            
			response.setMessage("Report created successfully");
			return response;		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}

}
