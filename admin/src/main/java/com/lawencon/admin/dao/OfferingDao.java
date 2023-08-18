package com.lawencon.admin.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.admin.model.Offering;
import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

@Repository
@Profile(value = { "native-query" })
public class OfferingDao extends AbstractJpaDao {
	
	public Offering getById(final Object id) {
		return super.getById(Offering.class, id);
	}

	public Offering getByIdRef(final Object id) {
		return super.getByIdRef(Offering.class, id);
	}

	public Offering getByIdAndDetach(final Object id) {
		return super.getByIdAndDetach(Offering.class, id);
	}
	
	public List<Offering> getAll() {
		return super.getAll(Offering.class);
	}

	public Offering save(Offering data) {
		return super.save(data);
	}

	public Offering saveAndFlush(Offering entity) {
		return super.saveAndFlush(entity);
	}

	public Offering saveNoLogin(Offering entity, Supplier<String> getIdFunc) {
		return super.saveNoLogin(entity, getIdFunc);
	}

	public boolean deleteById(final Object entityId) {
		return super.deleteById(Offering.class, entityId);
	}
	public Offering getByAppliedVacancyId(String appliedVacancyId) {
		final String sql = "SELECT "
				+ "to.id, to.is_approve, to.start_date, to.end_date, to.description, to.offering_location "
				+ "FROM "
				+ "t_offering to "
				+ "WHERE "
				+ "	to.applied_vacancy_id = :appliedVacancyId";
		
		final Object offObj = ConnHandler.getManager()
				.createNativeQuery(sql)
				.setParameter("appliedVacancyId", appliedVacancyId)
				.getSingleResult();
		
		final Object[] offObjArr = (Object[]) offObj;
		
		try {
			final Offering offering = new Offering();
			offering.setId(offObjArr[0].toString());
			offering.setIsApprove(Boolean.valueOf(offObjArr[1].toString()));
			offering.setStartDate(LocalDate.parse(offObjArr[2].toString()));
			offering.setEndDate(LocalDate.parse(offObjArr[3].toString()));
			offering.setDescription(offObjArr[4].toString());
			offering.setOfferingLocation(offObjArr[5].toString());
			
			return offering;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
