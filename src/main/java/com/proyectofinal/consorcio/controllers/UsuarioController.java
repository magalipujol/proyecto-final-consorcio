package com.proyectofinal.consorcio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyectofinal.consorcio.services.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/cambiar-contrasenia")
	public String cambiarContrasenia(ModelMap model) throws Exception {
		try {			
			return "cambiarcontrasenia.html";			
		} catch (Exception e) {
			throw new Exception("Error en controlador cambiar contraseña");
		}

	}

	@PostMapping("/cambiar-contrasenia")
	public String modificarContrasenia(@RequestParam String contrasenia1, @RequestParam String contrasenia2) throws Exception {
		try {
			usuarioService.cambiarContrasenia(contrasenia1, contrasenia2);

			return "redirect:/logout";
		} catch (Exception e) {
			throw new Exception("Error al modificar contraseña");
		}

	}

}
