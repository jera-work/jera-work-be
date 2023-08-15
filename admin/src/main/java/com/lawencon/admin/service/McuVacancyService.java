package com.lawencon.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.AppliedVacancyDao;
import com.lawencon.admin.dao.FileDao;
import com.lawencon.admin.dao.McuVacancyDao;
import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.mcuvacancy.InsertMcuVacancyReqDto;
import com.lawencon.admin.model.File;
import com.lawencon.admin.model.McuVacancy;
import com.lawencon.base.ConnHandler;

@Service
public class McuVacancyService {

	@Autowired
	private McuVacancyDao mcuDao;
	@Autowired
	private AppliedVacancyDao appliedVacancyDao;
	@Autowired
	private FileDao fileDao;

	public InsertResDto insertMcu(InsertMcuVacancyReqDto data) {

		try {
			ConnHandler.begin();
			final McuVacancy mcu = new McuVacancy();
			mcu.setAppliedVacancy(appliedVacancyDao.getByIdRef(data.getAppliedVacancy()));
			mcu.setEndDate(data.getEndDate());

			if (data.getFileContent() != null) {
				final File file = new File();
				file.setFileContent(data.getFileContent());
				file.setFileExt(data.getFileExt());
				final File fileDb = fileDao.saveAndFlush(file);
				mcu.setFile(fileDb);
			}

			mcu.setStartDate(data.getStartDate());
			final McuVacancy mcuDb = mcuDao.saveAndFlush(mcu);
			ConnHandler.commit();

			final InsertResDto response = new InsertResDto();
			response.setId(mcuDb.getId());
			response.setMessage("MCU has been created!");

			return response;
		} catch (Exception e) {
			e.printStackTrace();
			ConnHandler.rollback();
			return null;
		}
	}

}
