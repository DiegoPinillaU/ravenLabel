package com.raven.label.springboot.app.service;

import java.util.List;

import com.raven.label.springboot.app.entity.Rol;
import com.raven.label.springboot.app.entity.Usuario;

public interface IUsuarioService {
	
	public List <Usuario> findAll();
	
	public Usuario findByNombre(String nombre);
	
	public void asignarRol(Integer usuarioId , Rol rol);
	
	public void save(Usuario usuario);
	
	public void delete(Integer id);
	

}
