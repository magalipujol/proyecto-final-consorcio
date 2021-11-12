package com.proyectofinal.consorcio.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyectofinal.consorcio.entities.Edificio;
import com.proyectofinal.consorcio.entities.Liquidacion;
import com.proyectofinal.consorcio.services.EdificioService;
import com.proyectofinal.consorcio.services.LiquidacionService;
import com.proyectofinal.consorcio.services.UsuarioService;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private LiquidacionService liquidacionService;
	
	@Autowired
	private EdificioService edificioService;
	
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
	public String loginresolver(HttpServletRequest request, ModelMap model, Authentication usuario) throws Exception{
    	try {
    		if (request.isUserInRole("ADMIN")) {
        		String mail = usuarioService.getUserByLogin().getMail();
        		model.addAttribute("mail", mail);
        		
        		List<Edificio> edificios = edificioService.listarActivos();
        		
        		model.addAttribute("edificios", edificios);
        		
        		return "verEdificios.html";
    		}
        	if (request.isUserInRole("USER")) {
        		
        		String id_usuario = usuarioService.getUserByLogin().getId();
        		
        		List<Liquidacion> liquidaciones = liquidacionService.listarLiquidacionesUsuario(id_usuario);
        		      		
        		model.addAttribute("liquidaciones", liquidaciones);
        		
    			return "expensasVistaUsuario.html";
    		}
    		return "errors.html";
		} catch (Exception e) {
			throw new Exception ("Error en loginsuccess controller");
		}
    	
	}
}
