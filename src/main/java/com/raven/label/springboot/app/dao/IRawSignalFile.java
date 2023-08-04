package com.raven.label.springboot.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.raven.label.springboot.app.entity.RawSignalFile;

public interface IRawSignalFile extends CrudRepository<RawSignalFile, Integer>{

}
