package com.raven.label.springboot.app.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raven.label.springboot.app.dao.IVideoFileDao;
import com.raven.label.springboot.app.entity.VideoFile;

@Service
public class VideoFileServiceImpl implements IVideoFileService{

	@Autowired
	private IVideoFileDao vfDao;
	
	@Override
	public Iterable<VideoFile> findAll() {
		// TODO Auto-generated method stub
		return vfDao.findAll();
	}

	@Override
	public VideoFile findById(Integer id) {
		// TODO Auto-generated method stub
		return vfDao.findById(id).orElse(null);
	}

	@Override
	public void save(VideoFile vf) {
		// TODO Auto-generated method stub
		vfDao.save(vf);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		vfDao.deleteById(id);
	}

	@Override
	public List<VideoFile> findByFecha(LocalDateTime fecha) {
		// TODO Auto-generated method stub
		return vfDao.findByFechaInicio(fecha);
	}

	@Override
	public List<VideoFile> findVideoFilesBetweenDates(LocalDateTime start, LocalDateTime end) {
		// TODO Auto-generated method stub
		return vfDao.findVideoFilesBetweenDates(start, end);
	}

	@Override
	public List<VideoFile> findVideoFilesBetweenDates(LocalDate start, LocalDate end) {
		// TODO Auto-generated method stub
		return vfDao.findVideoFilesBetweenDates(start.atStartOfDay(), end.atStartOfDay());
	}

}
