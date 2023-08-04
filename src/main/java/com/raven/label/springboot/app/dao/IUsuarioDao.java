package com.raven.label.springboot.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.raven.label.springboot.app.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Integer> {
	
	public Usuario findByUsername(String username);
	

}
