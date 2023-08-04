package com.raven.label.springboot.app.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.raven.label.springboot.app.entity.VideoFile;

public interface IVideoFileService {

	public Iterable<VideoFile> findAll();
	
	public VideoFile findById(Integer id);
	
	public List<VideoFile> findByFecha(LocalDateTime fecha);
	
	public List<VideoFile> findVideoFilesBetweenDates(LocalDate localDate, LocalDate endDate);
	
	public List<VideoFile> findVideoFilesBetweenDates(LocalDateTime localDateTime, LocalDateTime endDateTime);
	
	public void save (VideoFile rol);
	
	public void delete (Integer id);
	
	
	
}
