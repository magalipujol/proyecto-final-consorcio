package com.proyectofinal.consorcio.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyectofinal.consorcio.entities.Departamento;
import com.proyectofinal.consorcio.entities.Edificio;
import com.proyectofinal.consorcio.entities.Liquidacion;
import com.proyectofinal.consorcio.services.DepartamentoService;
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
	
	@Autowired
	private DepartamentoService departamentoService;
	
	@GetMapping("/")
	public String index(ModelMap modelo) {
		return "index";
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/liquidaciones/ver")
	public String verLiquidaciones(ModelMap model) throws Exception{
    	try {
			String id_usuario = usuarioService.getUserByLogin().getId();
			List<Liquidacion> listaLiquidaciones = liquidacionService.listarLiquidacionesUsuario(id_usuario);
			Departamento departamento = departamentoService.buscarPorUsuario(id_usuario);

			model.addAttribute("liquidaciones", listaLiquidaciones);
			model.addAttribute("departamento", departamento);

			return "expensasVistaAdmi.html";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "expensasVistaAdmi.html";
		}
	}

    @GetMapping("/login")
	public String login(HttpSession session, Authentication usuario, ModelMap modelo, @RequestParam(required = false) String error) {
		try {
			if (usuario.getName() != null) {
				return "redirect:/";
			} else {				
				if (error != null && !error.isEmpty())
					modelo.addAttribute("error", "El usuario o la contraseña que ingresó son incorrectos.");				

				return "login";
			}			
		} catch (Exception e) {
			if (error != null && !error.isEmpty())
				modelo.addAttribute("error", "El usuario o la contraseña que ingresó son incorrectos.");			

			return "login";
		}
	}
    
    @GetMapping("/loginsuccess")
	public String loginresolver(HttpServletRequest request, ModelMap model, Authentication usuario) throws Exception{
    	try {
    		if (request.isUserInRole("ADMIN")) {
        		String mail = usuarioService.getUserByLogin().getMail();
        		List<Edificio> listaEdificios = edificioService.listarActivos();

        		model.addAttribute("mail", mail);        	        		
        		model.addAttribute("edificios", listaEdificios);
        		
        		return "verEdificios.html";
    		}

        	if (request.isUserInRole("USER")) {        		
        		String id_usuario = usuarioService.getUserByLogin().getId();        		
        		List<Liquidacion> listaLiquidaciones = liquidacionService.listarLiquidacionesUsuario(id_usuario);
				Departamento departamento = departamentoService.buscarPorUsuario(id_usuario);
        		      		
        		model.addAttribute("liquidaciones", listaLiquidaciones);
				model.addAttribute("departamento", departamento);
        		
    			return "expensasVistaAdmi.html";
    		}
			
    		return "index.html";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "index.html";
		}    	
	}
}
