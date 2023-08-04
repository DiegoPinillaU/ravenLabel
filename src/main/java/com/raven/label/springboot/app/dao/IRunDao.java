package com.raven.label.springboot.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.raven.label.springboot.app.entity.Run;

public interface IRunDao extends CrudRepository<Run, Integer>{

}
