package com.lawencon.candidate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.candidate.dao.FileDao;
import com.lawencon.candidate.model.File;

@Service
public class FileService {

	@Autowired
	private FileDao fileDao;

	public File getById(String id) {
		ConnHandler.begin();
		final File file = fileDao.getById(id);
		ConnHandler.commit();
		return file;
	}

	public File insertFile(File file) {
		ConnHandler.begin();
		final File files = fileDao.save(file);
		ConnHandler.commit();
		return files;
	}

	public Boolean deleteById(String id) {
		ConnHandler.begin();
		final Boolean delete = fileDao.deleteById(id);
		ConnHandler.commit();
		return delete;
	}

}
