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

import com.proyectofinal.consorcio.entities.Edificio;
import com.proyectofinal.consorcio.entities.Egreso;
import com.proyectofinal.consorcio.entities.Liquidacion;
import com.proyectofinal.consorcio.services.EdificioService;
import com.proyectofinal.consorcio.services.EgresoService;
import com.proyectofinal.consorcio.services.LiquidacionService;

@Controller
@RequestMapping("/liquidaciones")
public class LiquidacionController {
	@Autowired
	private LiquidacionService liquidacionService;

	@Autowired
	private EdificioService edificioService;
	
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
