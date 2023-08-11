package com.lawencon.admin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.File;
import com.lawencon.admin.model.Profile;
import com.lawencon.admin.model.Role;
import com.lawencon.admin.model.User;
import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

@Repository
@org.springframework.context.annotation.Profile(value = { "native-query" })
public class UserDao extends AbstractJpaDao {
	
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
					+ "tu.id, tu.user_password , tp.profile_name, tf.id as fileId, tr.role_code "
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
		final User user = new User();
		try {
			final Object userObj = ConnHandler.getManager().createNativeQuery(sql)
					.setParameter("email", email)
					.getSingleResult();
			
			final Object[] userArr = (Object[]) userObj;
			
			if(userArr.length > 0) {
				user.setId(userArr[0].toString());
				user.setUserPassword(userArr[1].toString());
				
				final Profile profile = new Profile();
				profile.setProfileName(userArr[2].toString());
				
				final File photo = new File();
				photo.setId(userArr[3].toString());
				profile.setPhoto(photo);
				
				final Role role = new Role();
				role.setRoleCode(userArr[4].toString());
				
				user.setProfile(profile);
				user.setRole(role);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
		
	}
	
	public List<User> getByRoleCode(String roleCode, String companyCode) {
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
					+ "	tr.role_code = :roleCode AND tc.company_code = :companyCode ";
		
		final List<?> userObj = ConnHandler.getManager().createNativeQuery(sql, User.class)
				.setParameter("roleCode", roleCode)
				.setParameter("companyCode", companyCode)
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

}