package com.raven.label.springboot.app.html.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

/*
 * GlobalControllerAdvice es una clase que captura las solicitudes
 * e incluye la URI de la ruta actual, es crítico para mostrar la
 * pestaña selecionada en la navegación superior
*/
@ControllerAdvice
public class GlobalControllerAdvice {
	
	@ModelAttribute
	public void exposeCurrentEndPoint(HttpServletRequest request, Model model) {
		model.addAttribute("currentURI", request.getRequestURI());
	}
}
