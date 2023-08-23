package com.lawencon.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.FileDao;
import com.lawencon.admin.model.File;
import com.lawencon.base.ConnHandler;

@Service
public class FileService {

	@Autowired
	private FileDao fileDao;

	public FileService(FileDao fileDao) {
		this.fileDao = fileDao;
	}


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
