package com.raven.label.springboot.app.form.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.raven.label.springboot.app.entity.Rol;
import com.raven.label.springboot.app.entity.Usuario;
import com.raven.label.springboot.app.service.IRolService;
import com.raven.label.springboot.app.service.IUsuarioService;
import jakarta.validation.Valid;

@Controller
public class UsuarioFormController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IRolService rolService;


	@PostMapping("/agregar-usuario-form")
	public String agregarUsuario (@Valid @ModelAttribute(value = "usuario") Usuario usuario ,
			BindingResult result,RedirectAttributes flash){
		
		
		usuarioService.save(usuario);
		String mensajeFlash = (usuario.getId() != null) ? "Usuario editado con éxito!" : "Usuario creado con éxito!";
	
		for (int i=0; i < usuario.getRoles().size(); i++) {
			Rol rol = new Rol();
			System.out.println(usuario.getRoles().get(i).getRol());
			rol.setRol(usuario.getRoles().get(i).getRol());
			usuarioService.asignarRol(usuario.getId(), rol);
		}
		return "redirect:/agregar-usuario";
	}


}
