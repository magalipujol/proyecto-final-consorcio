package com.proyectofinal.consorcio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyectofinal.consorcio.entities.Departamento;
import com.proyectofinal.consorcio.entities.Edificio;
import com.proyectofinal.consorcio.services.DepartamentoService;
import com.proyectofinal.consorcio.services.EdificioService;

@Controller
@RequestMapping("/edificios")
public class EdificioController {
    @Autowired
    private EdificioService edificioService;
    @Autowired
    private DepartamentoService departamentoService;

    @PostMapping("/crearedificio")
    public String crear(@RequestParam String direccion, ModelMap model) throws Exception{
    	
    	Edificio edificio;
		try {
			edificio = edificioService.crear(direccion);
			model.addAttribute("edificio", edificio.getId());
			List<Departamento>departamentos= departamentoService.listarActivos(edificio.getId());
			model.addAttribute("departamentos", departamentos);
	        return "formAgregarDepartamento.html";
		} catch (Exception e) {
			throw new Exception ("Error en controlador edificio");
		}
   
    }
}
