package com.proyectofinal.consorcio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyectofinal.consorcio.entities.Departamento;
import com.proyectofinal.consorcio.entities.Edificio;
import com.proyectofinal.consorcio.entities.Egreso;
import com.proyectofinal.consorcio.entities.Liquidacion;
import com.proyectofinal.consorcio.entities.Usuario;
import com.proyectofinal.consorcio.services.DepartamentoService;
import com.proyectofinal.consorcio.services.EdificioService;
import com.proyectofinal.consorcio.services.EgresoService;
import com.proyectofinal.consorcio.services.LiquidacionService;
import com.proyectofinal.consorcio.services.UsuarioService;

@Controller
@RequestMapping("/liquidaciones")
public class LiquidacionController {
	@Autowired
	private LiquidacionService liquidacionService;

	@Autowired
	private EdificioService edificioService;
	
	@Autowired
	private DepartamentoService departamentoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private EgresoService egresoService;

	@GetMapping("/cargar/{id_edificio}")
	public String crearLiquidacion(ModelMap model,@PathVariable Long id_edificio) throws Exception {
		try {
			Edificio edificio = edificioService.buscarPorId(id_edificio);

			model.addAttribute("edificio", edificio);

			return "cargarMovimientos.html";
		} catch (Exception e) {
			throw new Exception("Error en controlador crearLiquidacion");
		}
	}
	
	 @GetMapping("/modificar/{id}")
	    public String modificar (@PathVariable String id, ModelMap model) throws Exception{
	    	try {
				Liquidacion liquidacion = liquidacionService.buscarPorId(id);

				model.addAttribute("liquidacion", liquidacion);
				
				List<Egreso> egresos = egresoService.listarActivosMes(id);
				
				model.addAttribute("egresos", egresos);
				
				return "cargarMovimientos.html";
			} catch (Exception e) {
				throw new Exception ("Error en controlador modificar liquidacion");
			}    	
	    }
	
	@GetMapping("/ver-lista/{id_edificio}")
	public String verLiquidacionesEdificio (ModelMap model,@PathVariable Long id_edificio) throws Exception {
		try {
			List<Liquidacion> liquidaciones = liquidacionService.listarLiquidacionesAdmin(id_edificio);

			model.addAttribute("liquidaciones", liquidaciones);

			return "expensasVistaAdmi.html";
		} catch (Exception e) {
			throw new Exception("Error en controlador verLiquidacionesEdificio");
		}
	}
	
	@GetMapping("/ver/{id}")
	public String verLiquidacion (ModelMap model,@PathVariable String id) throws Exception {
		try {
			Liquidacion liquidacion = liquidacionService.buscarPorId(id);
			
			model.addAttribute("liquidacion", liquidacion);
			
			Usuario usuario = usuarioService.getUserByLogin();
			
			Departamento departamento = departamentoService.buscarPorUsuario(usuario.getId());
			
			model.addAttribute("departamento", departamento);
			
			List<Egreso> egresos = egresoService.listarActivosMes(id);

			model.addAttribute("egresos", egresos);
			
			//Retorna vista de una liquidacion
			return "expensasVistaUsuario.html";
		} catch (Exception e) {
			throw new Exception("Error en controlador verLiquidacion");
		}
	}

	@PostMapping("/guardar")
	public String guardarLiquidacion(ModelMap model, @RequestParam String mes,
			@RequestParam Integer yearpicker, @RequestParam Long id_edificio) throws Exception {		
		try {
			Liquidacion liquidacion = liquidacionService.crear(mes, yearpicker, id_edificio);

			model.addAttribute("liquidacion", liquidacion);
			
			Edificio edificio = edificioService.buscarPorId(id_edificio);
			
			model.addAttribute("edificio", edificio);
			
			return "cargarMovimientos.html";
		} catch (Exception e) {
			throw new Exception("Error en controlador guardarLiquidacion");
		}
	}
	
	@PostMapping("/liquidar/")
	public String liquidarExpensas(ModelMap model,@RequestParam String id) throws Exception{
		try {
			Double totalOrdinarios = liquidacionService.totalOrdinarios(id);
			Double totalExtraordinarios = liquidacionService.totalExtraordinarios(id);
			Double total = liquidacionService.total(id);
			List<Egreso> listaEgresos = egresoService.listarActivosMes(id);

			model.addAttribute("ordinarios", totalOrdinarios);			
			model.addAttribute("extraordinarios", totalExtraordinarios);			
			model.addAttribute("total", total);			
			model.addAttribute("egresos", listaEgresos);
			
			liquidacionService.publicar(id);
			
			return "expensasVistaAdmi.html";
		} catch (Exception e) {
			throw new Exception ("Error en controlador liquidarExpensas");
		}
	}
}
