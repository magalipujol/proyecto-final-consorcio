package com.proyectofinal.consorcio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyectofinal.consorcio.entities.Departamento;
import com.proyectofinal.consorcio.entities.Usuario;
import com.proyectofinal.consorcio.services.DepartamentoService;
import com.proyectofinal.consorcio.services.UsuarioService;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoService departamentoService;
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/agregar")
	public String agregarDpto (@RequestParam Integer piso,@RequestParam String dpto,
			@RequestParam Double porcentajeParticipacion,@RequestParam String mail,@RequestParam Long id_edificio, ModelMap model) throws Exception{	
		
			Usuario usuario = usuarioService.crearUsuario(mail);
			departamentoService.crear(piso, dpto, porcentajeParticipacion, id_edificio, usuario);
			model.addAttribute("edificio", id_edificio);
			List<Departamento>departamentos= departamentoService.listarActivos(id_edificio);
			model.addAttribute("departamentos", departamentos);
	        return "formAgregarDepartamento.html";
	
	}
}
