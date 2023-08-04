package com.raven.label.springboot.app.dao;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import com.raven.label.springboot.app.entity.VideoFile;

public interface IVideoFileDao extends CrudRepository<VideoFile, Integer>{

	List<VideoFile> findByFechaInicio(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:SS") LocalDateTime fecha);
	List<VideoFile> findByFechaInicioBetween(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:SS") LocalDateTime start, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:SS") LocalDateTime end);
	
	@Query("SELECT vid FROM VideoFile vid JOIN ReferenceSignalFile rs WHERE vid.fechaInicio BETWEEN :start AND :end")
	List<VideoFile> findVideoFilesBetweenDates(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
//	@Override
//	default Iterable<VideoFile> findAll(Sort sort) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	default Page<VideoFile> findAll(Pageable pageable) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
}
