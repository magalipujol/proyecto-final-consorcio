package com.proyectofinal.consorcio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyectofinal.consorcio.entities.Departamento;
import com.proyectofinal.consorcio.entities.Liquidacion;
import com.proyectofinal.consorcio.entities.Usuario;
import com.proyectofinal.consorcio.services.DepartamentoService;
import com.proyectofinal.consorcio.services.LiquidacionService;
import com.proyectofinal.consorcio.services.UsuarioService;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {
	@Autowired
	private DepartamentoService departamentoService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private LiquidacionService liquidacionService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/ver/{id_liquidacion}")
	public String verTotalesPorDepto (@PathVariable String id_liquidacion, ModelMap model) throws Exception {
		try {
			Liquidacion liquidacion = liquidacionService.buscarPorId(id_liquidacion);
			
			model.addAttribute("liquidacion", liquidacion);
			
			Long id_edificio = liquidacion.getEdificio().getId();
			
			List<Departamento> departamentos = departamentoService.listarActivos(id_edificio);

			model.addAttribute("departamentos", departamentos);
			
			List<Double> totalesDpto = departamentoService.totalesDepartamentos(id_liquidacion);
			
			model.addAttribute("totalesDpto", totalesDpto);
			
			return "totalesdpto.html";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "totalesdpto.html";
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/modificar/{id}")
	public String modificar(@PathVariable String id, ModelMap model) throws Exception {
		try {
			Departamento departamento = departamentoService.buscarPorId(id);

			model.addAttribute("departamento", departamento);

			return "formAgregarDepartamento.html";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "formAgregarDepartamento.html";
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/eliminar/{id}")
	public String darDeBaja(@PathVariable String id, ModelMap model) throws Exception {
		try {
			departamentoService.eliminar(id);

			Departamento departamento = departamentoService.buscarPorId(id);			
			model.addAttribute("departamento", departamento);			

			return "verEdificios.html";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "verEdificios.html";
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/agregar")
	public String agregarDpto(@RequestParam(required = false) String id, @RequestParam Integer piso,
			@RequestParam String dpto, @RequestParam Double porcentajeParticipacion, @RequestParam String mail,
			@RequestParam Long id_edificio, ModelMap model) throws Exception {
		try {
			if (id == null) {
				Usuario usuario = usuarioService.crearUsuario(mail);
				departamentoService.crear(piso, dpto, porcentajeParticipacion, id_edificio, usuario);								
			} else {
				departamentoService.modificar(id, piso, dpto, porcentajeParticipacion);
			}

			List<Departamento> listaDepartamentos = departamentoService.listarActivos(id_edificio);
			model.addAttribute("edificio", id_edificio);
			model.addAttribute("departamentos", listaDepartamentos);

			return "formAgregarDepartamento.html";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "formAgregarDepartamento.html";
		}
	}
}
