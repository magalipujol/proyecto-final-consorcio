package com.proyectofinal.consorcio.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class IndexController {
	@GetMapping("/")
	public String index(ModelMap modelo) {
		return "index";
	}

    @GetMapping("/login")
	public String login(HttpSession session, Authentication usuario, ModelMap modelo, @RequestParam(required = false) String error) {
		try {
			if (usuario.getName() != null) {
				// devuelve esto si esta logeado con usuario yendo a local/login
				return "redirect:/";
			} else {				
				if (error != null && !error.isEmpty())
					modelo.addAttribute("error", "La dirección de mail o la contraseña que ingresó son incorrectas.");				

				// devuelve esto si no esta logeado o le erro a algo
				return "login";
			}			
		} catch (Exception e) {
			if (error != null && !error.isEmpty())
				modelo.addAttribute("error", "La dirección de mail o la contraseña que ingresó son incorrectas.");			

			// devuelve esto si no esta logeado o le erro a algo
			return "login";
		}
	}

    @GetMapping("/loginsuccess")
	public String loginresolver() {				
		return "formCrearEdificio.html";
	}
}
