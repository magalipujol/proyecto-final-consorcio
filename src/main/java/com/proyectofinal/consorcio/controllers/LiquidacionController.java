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
			//<a class="btn btn-primary" th:href="@{./cargar/}+${edificio}" role="button">Cargar Liquidacion</a>
			return "cargarMovimientos.html";
		} catch (Exception e) {
			throw new Exception("Error en controlador crearLiquidacion");
		}
	}

	@PostMapping("/guardar")
	public String guardarLiquidacion(ModelMap model,@RequestParam String mes,
			@RequestParam Integer anio,@RequestParam Long id_edificio) throws Exception {
		
		try {
			Liquidacion liquidacion = liquidacionService.crear(mes, anio, id_edificio);
			model.addAttribute("liquidacion", liquidacion);

			return "formSubirExpensas.html";

		} catch (Exception e) {
			throw new Exception("Error en controlador guardarLiquidacion");
		}
	}
	
	@PostMapping("/liquidar")
	public String liquidarExpensas(ModelMap model, String id) throws Exception{
		try {
			Double totalOrdinarios = liquidacionService.totalOrdinarios(id);
			model.addAttribute("ordinarios", totalOrdinarios);
			
			Double totalExtraordinarios = liquidacionService.totalExtraordinarios(id);
			model.addAttribute("extraordinarios", totalExtraordinarios);
			
			Double total = liquidacionService.total(id);
			model.addAttribute("total", total);
			
			List<Egreso> egresos = egresoService.listarActivosMes(id);
			model.addAttribute("egresos", egresos);
			
			liquidacionService.publicar(id);
			
			return "ver-liquidacion.html";
		} catch (Exception e) {
			throw new Exception ("Error en controlador liquidarExpensas");
		}
	}

}
