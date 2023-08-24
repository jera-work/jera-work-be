package com.lawencon.admin.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.McuVacancy;
import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

@Repository
public class McuVacancyDao extends AbstractJpaDao {
	
	public McuVacancy getById(final Object id) {
		return super.getById(McuVacancy.class, id);
	}

	public McuVacancy getByIdRef(final Object id) {
		return super.getByIdRef(McuVacancy.class, id);
	}

	public McuVacancy getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(McuVacancy.class, id);
	}
	
	public List<McuVacancy> getAll() {
		return super.getAll(McuVacancy.class);
	}

	public McuVacancy save(McuVacancy data) {
		return super.save(data);
	}

	public McuVacancy saveAndFlush(McuVacancy entity) {
		return super.saveAndFlush(entity);
	}

	public McuVacancy saveNoLogin(McuVacancy entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(McuVacancy.class, entityId);
	}

	public McuVacancy getByAppliedVacancyId(String appliedVacancyId) {
		final String sql = "SELECT "
				+ "tmv.id, tmv.start_date, tmv.end_date"
				+ "FROM "
				+ "t_mcu_vacancy tmv "
				+ "WHERE "
				+ "	tmv.applied_vacancy_id = :appliedVacancyId";
		
		final Object mcuObj = ConnHandler.getManager()
				.createNativeQuery(sql)
				.setParameter("appliedVacancyId", appliedVacancyId)
				.getSingleResult();
		
		final Object[] mcuObjArr = (Object[]) mcuObj;
		
		try {
			final McuVacancy mcuVacancy = new McuVacancy();
			mcuVacancy.setId(mcuObjArr[0].toString());
			mcuVacancy.setStartDate(LocalDate.parse(mcuObjArr[1].toString()));
			mcuVacancy.setEndDate(LocalDate.parse(mcuObjArr[2].toString()));
			
			return mcuVacancy;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}