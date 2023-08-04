package com.raven.label.springboot.app.service;

import java.util.List;

import com.raven.label.springboot.app.entity.Rol;

public interface IRolService {

	public List<Rol> findAll();
	
	public Rol findById(Integer id);
	
	public void save (Rol rol);
	
	public void delete (Integer id);
	
	
	
}
