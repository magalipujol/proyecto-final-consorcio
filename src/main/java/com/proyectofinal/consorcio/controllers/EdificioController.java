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
import com.proyectofinal.consorcio.entities.Liquidacion;
import com.proyectofinal.consorcio.services.DepartamentoService;
import com.proyectofinal.consorcio.services.EdificioService;
import com.proyectofinal.consorcio.services.LiquidacionService;
import com.proyectofinal.consorcio.services.UsuarioService;

@Controller
@RequestMapping("/edificios")
public class EdificioController {
    @Autowired
    private EdificioService edificioService;
    @Autowired
    private DepartamentoService departamentoService;
    @Autowired
    private LiquidacionService liquidacionService;
    @Autowired
    private UsuarioService usuarioService;
    
    
    
	@GetMapping("/ver")
    public String verEdificios (ModelMap model) throws Exception {
    	try {
    		String mail = usuarioService.getUserByLogin().getMail();
    		List<Edificio> listaEdificios = edificioService.listarActivos();

    		model.addAttribute("mail", mail);        	        		
    		model.addAttribute("edificios", listaEdificios);
    		
    		return "verEdificios.html";
		} catch (Exception e) {
			throw new Exception ("Error en controlador verLiquidaciones");
		}
    }
	
    @GetMapping("/ver-liquidaciones/{id}")
    public String verLiquidaciones (@PathVariable Long id, ModelMap model) throws Exception {
    	try {
			List<Liquidacion> listaLiquidaciones = liquidacionService.listarLiquidacionesAdmin(id);

			model.addAttribute("liquidaciones", listaLiquidaciones);

			return "lista-liquidaciones.html";
		} catch (Exception e) {
			throw new Exception ("Error en controlador verLiquidaciones");
		}
    }
    
    @GetMapping("/crearedificio")
    public String crearEdificio() throws Exception {
    	try {
			return "formCrearEdificio.html";
		} catch (Exception e) {
			throw new Exception ("Error en controlador crearEdificio");
		}
    }
    
    //Ver front para que llegue el id_edificio
    @PostMapping("/crearedificio")
    public String crear(@RequestParam(required = false) Long id, @RequestParam String direccion, ModelMap model) throws Exception{
		try {
			Edificio edificio;

			if (id == null) {
				edificio = edificioService.crear(direccion);				
			} else {
				edificio = edificioService.modificar(id, direccion);
			}

			List<Departamento> listaDepartamentos = departamentoService.listarActivos(edificio.getId());

			model.addAttribute("edificio", edificio.getId());
			model.addAttribute("departamentos", listaDepartamentos);

	        return "formAgregarDepartamento.html";
		} catch (Exception e) {
			throw new Exception ("Error en controlador crear edificio");
		}
    }
    
    //Ver input hidden en caso de modificar
    @GetMapping("/modificar/{id}")
    public String modificar (@PathVariable Long id, ModelMap model) throws Exception{
    	try {
			Edificio edificio = edificioService.buscarPorId(id);

			model.addAttribute("edificio", edificio);

			return "formCrearEdificio.html";
		} catch (Exception e) {
			throw new Exception ("Error en controlador modificar edificio");
		}    	
    }
    
    //PreAuthorize agregar    
    @GetMapping("/eliminar/{id}")
    public String darDeBaja (@PathVariable Long id, ModelMap model) throws Exception{
    	try {
			Edificio edificio = edificioService.buscarPorId(id);

			edificioService.darDeBaja(id);

			model.addAttribute("edificio", edificio);

			return "formCrearEdificio.html";
		} catch (Exception e) {
			throw new Exception ("Error en controlador darDeBaja edificio");
		}    	
    }    
}
