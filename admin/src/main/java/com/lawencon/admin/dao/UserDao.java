package com.lawencon.admin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.Company;
import com.lawencon.admin.model.File;
import com.lawencon.admin.model.Profile;
import com.lawencon.admin.model.Role;
import com.lawencon.admin.model.User;
import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

@Repository
public class UserDao extends AbstractJpaDao {
	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	public User getById(final Object id) {
		return super.getById(User.class, id);
	}

	public User getByIdRef(final Object id) {
		return super.getByIdRef(User.class, id);
	}

	public User getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(User.class, id);
	}
	
	public List<User> getAll() {
		return super.getAll(User.class);
	}

	public User save(User data) {
		return super.save(data);
	}

	public User saveAndFlush(User entity) {
		return super.saveAndFlush(entity);
	}

	public User saveNoLogin(User entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(User.class, entityId);
	}
	
	public User getByEmail(String email) {
		final String sql = "SELECT "
				+ "tu.id, tu.user_password , tp.profile_name, tp.company_id, tf.id as fileId, tr.role_code "
				+ "FROM "
				+ "t_user tu "
				+ "INNER JOIN "
				+ "t_role tr ON tu.role_id = tr.id "
				+ "INNER JOIN "
				+ "t_profile tp ON tu.profile_id = tp.id "
				+ "LEFT JOIN "
				+ "t_file tf ON tp.photo_id  = tf.id "
				+ "WHERE "
				+ "tu.user_email = :email";
		
			final Object userObj = em().createNativeQuery(sql)
					.setParameter("email", email)
					.getSingleResult();
			
			final Object[] userArr = (Object[]) userObj;

			User user = null;
			if(userArr.length > 0) {
				user = new User();
				user.setId(userArr[0].toString());
				user.setUserPassword(userArr[1].toString());
				
				final Profile profile = new Profile();
				profile.setProfileName(userArr[2].toString());
				
				final Company company = new Company();
				company.setId(userArr[3].toString());
				profile.setCompany(company);
				
				if(userArr[4] != null) {
					final File photo = new File();
					photo.setId(userArr[4].toString());
					profile.setPhoto(photo);
				}
				
				final Role role = new Role();
				role.setRoleCode(userArr[5].toString());
				
				user.setProfile(profile);
				user.setRole(role);

			}

			return user;
	}
	
	public List<User> getByRoleCode(String roleCode, String companyId) {
		final List<User> users = new ArrayList<>();
		final String sql =
				"SELECT "
				+ "	tu.id, tp.profile_name "
				+ "FROM "
				+ "	t_user tu "
				+ "INNER JOIN "
				+ "	t_role tr ON tu.role_id = tr.id "
				+ "INNER JOIN "
				+ "	t_profile tp ON tu.profile_id = tp.id "
				+ "INNER JOIN "
				+ "	t_company tc ON tp.company_id = tc.id "
				+ "WHERE "
				+ "	tr.role_code = :roleCode AND tp.company_id = :companyId ";
		
		final List<?> userObj = em().createNativeQuery(sql)
				.setParameter("roleCode", roleCode)
				.setParameter("companyId", companyId)
				.getResultList();
		
		if(userObj.size() > 0) {
			for(Object u : userObj) {
				final Object[] userArr = (Object[]) u;
				
				final Profile profile = new Profile();
				profile.setProfileName(userArr[1].toString());
				
				final User user = new User();
				user.setId(userArr[0].toString());
				user.setProfile(profile);
				users.add(user);
			}
		}
		return users;
	}

	public List<User> getByCompany(String companyId) {
		final List<User> users = new ArrayList<>();
		final String sql =
				"SELECT "
				+ "	tu.id, tp.profile_name, tc.company_name, tr.role_name, tp.profile_phone, tp.profile_address, tp.photo_id "
				+ "FROM "
				+ "	t_user tu "
				+ "INNER JOIN "
				+ "	t_role tr ON tu.role_id = tr.id "
				+ "INNER JOIN "
				+ "	t_profile tp ON tu.profile_id = tp.id "
				+ "INNER JOIN "
				+ "	t_company tc ON tp.company_id = tc.id "
				+ "WHERE "
				+ "	tp.company_id = :companyId ";
		
		final List<?> userObj = em().createNativeQuery(sql)
				.setParameter("companyId", companyId)
				.getResultList();
		
		if(userObj.size() > 0) {
			for(Object u : userObj) {
				final Object[] userArr = (Object[]) u;
								
				final Company company = new Company();
				company.setCompanyName(userArr[2].toString());
				
				final Profile profile = new Profile();
				profile.setProfileName(userArr[1].toString());
				profile.setCompany(company);
				if(userArr[4] != null) {
					profile.setProfilePhone(userArr[4].toString());					
				}
				if(userArr[5] != null) {
					profile.setProfileAddress(userArr[5].toString());					
				}
				if(userArr[6] != null) {
					final File file = new File();
					file.setId(userArr[6].toString());
					profile.setPhoto(file);
				}
				
				final Role role = new Role();
				role.setRoleName(userArr[3].toString());
				
				final User user = new User();
				user.setId(userArr[0].toString());
				user.setProfile(profile);
				user.setRole(role);
				
				users.add(user);
				
			}
		}
		return users;
	}
}