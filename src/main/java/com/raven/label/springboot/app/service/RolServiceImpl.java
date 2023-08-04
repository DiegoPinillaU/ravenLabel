package com.raven.label.springboot.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raven.label.springboot.app.dao.IRolDao;
import com.raven.label.springboot.app.entity.Rol;

@Service
public class RolServiceImpl implements IRolService{

	@Autowired
	private IRolDao rolDao;

	@Override
	@Transactional(readOnly = true)
	public List<Rol> findAll() {
		// TODO Auto-generated method stub
		return (List<Rol>)rolDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Rol findById(Integer id) {
		// TODO Auto-generated method stub
		return rolDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void save(Rol rol) {
		// TODO Auto-generated method stub
		rolDao.save(rol);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		rolDao.deleteById(id);
	}
	
	
	
	
	
}
