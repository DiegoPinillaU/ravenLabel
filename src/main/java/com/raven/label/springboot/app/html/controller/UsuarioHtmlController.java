package com.raven.label.springboot.app.html.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.raven.label.springboot.app.entity.Usuario;

@Controller
public class UsuarioHtmlController {

	
	@GetMapping("/agregar-usuario")
	public String agregarUsuario(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		
		List <String> roles = new ArrayList<>();
		roles.add(0, "ROLE_ADMIN");
		roles.add(1, "ROLE_USER");
		model.addAttribute("roles", roles);
		return "agregar-usuario";	
	}

	
	
}
