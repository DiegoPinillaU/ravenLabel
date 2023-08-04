package com.raven.label.springboot.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raven.label.springboot.app.dao.IRolDao;
import com.raven.label.springboot.app.dao.IUsuarioDao;
import com.raven.label.springboot.app.entity.Rol;
import com.raven.label.springboot.app.entity.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private IRolDao rolDao;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findByNombre(String nombre) {
		// TODO Auto-generated method stub
		return usuarioDao.findByUsername(nombre);
	}

	@Override
	@Transactional
	public void save(Usuario usuario) {
		// TODO Auto-generated method stub
		usuarioDao.save(usuario);
	}
	
	@Override
	@Transactional
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		usuarioDao.deleteById(id);
	}

	@Override
	@Transactional
	public void asignarRol(Integer usuarioId, Rol rol) {
		// TODO Auto-generated method stub
		Usuario usuario = usuarioDao.findById(usuarioId).orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
		rol.setUsuario(usuario);
		usuario.getRoles().add(rol);
		usuarioDao.save(usuario);
	}
	
}
