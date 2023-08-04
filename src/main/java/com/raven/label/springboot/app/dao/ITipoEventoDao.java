package com.raven.label.springboot.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.raven.label.springboot.app.entity.TipoEvento;

public interface ITipoEventoDao extends CrudRepository<TipoEvento, Integer>{

}
