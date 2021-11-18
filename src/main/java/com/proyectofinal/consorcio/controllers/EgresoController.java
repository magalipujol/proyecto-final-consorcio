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
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/eliminar/{id}")
    public String eliminar (@PathVariable String id, ModelMap model) throws Exception{
    	try {
			egresoService.eliminar(id);
			
			Egreso egreso = egresoService.buscarPorId(id);
			
			model.addAttribute("egreso", egreso);
			
			Liquidacion liquidacion = egreso.getLiquidacion();
			
			model.addAttribute("liquidacion", liquidacion);	
			
			List<Egreso> egresos = egresoService.listarActivosMes(liquidacion.getId());
			
			model.addAttribute("egresos", egresos);
			
			return "cargarMovimientos.html";
		} catch (Exception e) {
			throw new Exception ("Error en controlador modificar liquidacion");
		}    	
    }
	
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
	@GetMapping("/ver/{id_liquidacion}")
	public String verLiquidacion (ModelMap model,@PathVariable String id_liquidacion) throws Exception {
		try {
			Liquidacion liquidacion = liquidacionService.buscarPorId(id_liquidacion);
			
			model.addAttribute("liquidacion", liquidacion);
			
			List<Egreso> ordinarios = egresoService.listarOrdinariosMes(id_liquidacion);

			model.addAttribute("ordinarios", ordinarios);
			
			List<Egreso> extraordinarios = egresoService.listarExtraordinariosMes(id_liquidacion);

			model.addAttribute("extraordinarios", extraordinarios);

			return "expensasVistaUsuario.html";
		} catch (Exception e) {
			throw new Exception("Error en controlador verLiquidacionesEdificio");
		}
	}
	
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/guardar")
	public String guardarEgresos (ModelMap model, @RequestParam String detalle, @RequestParam Double monto,
			@RequestParam String tipoGasto, @RequestParam String id_liquidacion) throws Exception{
		try {
			egresoService.crear(detalle, monto, tipoGasto, id_liquidacion);

			Liquidacion liquidacion = liquidacionService.buscarPorId(id_liquidacion);
			List<Egreso> listaEgresos = egresoService.listarActivosMes(id_liquidacion);

			model.addAttribute("egresos", listaEgresos);
			model.addAttribute("liquidacion", liquidacion);			
				
			return "cargarMovimientos.html";
		} catch (Exception e) {
			throw new Exception ("Error en controlador guardarEgresos");
		}	
	}	
}
