package com.proyectofinal.consorcio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyectofinal.consorcio.entities.Egreso;
import com.proyectofinal.consorcio.entities.Liquidacion;
import com.proyectofinal.consorcio.services.EgresoService;
import com.proyectofinal.consorcio.services.LiquidacionService;

@Controller
@RequestMapping("/egresos")
public class EgresoController {
	
	@Autowired
	private LiquidacionService liquidacionService;
	
	@Autowired
	private EgresoService egresoService;
	
	//Vista solo para el admin	
	@PostMapping("/guardar")
	public String guardarEgresos (ModelMap model,@RequestParam String detalle,@RequestParam Double monto,
			@RequestParam String tipoGasto,@RequestParam String id_liquidacion) throws Exception{
		try {
			Liquidacion liquidacion = liquidacionService.buscarPorId(id_liquidacion);
			model.addAttribute("liquidacion", liquidacion);
			List<Egreso> egresos = egresoService.listarActivosMes(id_liquidacion);
			model.addAttribute("egresos", egresos);
			egresoService.crear(detalle, monto, tipoGasto, id_liquidacion);
			
			return "formSubirExpensas.html";
		} catch (Exception e) {
			throw new Exception ("Error en controlador guardarEgresos");
		}
	
	}
	
	
}
