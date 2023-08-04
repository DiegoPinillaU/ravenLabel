package com.raven.label.springboot.app.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raven.label.springboot.app.dao.IUsuarioDao;
import com.raven.label.springboot.app.entity.Rol;
import com.raven.label.springboot.app.entity.Usuario;


@Service("jpaUserDetailService")
public class JpaUserDetailService implements UserDetailsService {

	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	private Logger logger = LoggerFactory.getLogger(JpaUserDetailService.class);
	
	@Override
	@Transactional(readOnly =  true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByUsername(username);
		
		if(usuario == null) {
			logger.error("Error login: no existe el usuario");
			throw new UsernameNotFoundException(username);
		}
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(Rol rol : usuario.getRoles()) {
			System.out.println("" + usuario.getUsername());
			authorities.add(new SimpleGrantedAuthority(rol.getRol()));	
		}
		
		if(authorities.isEmpty()) {
			logger.error("Error login: no tiene roles");
			throw new UsernameNotFoundException("sin roles");
		}
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}

}
