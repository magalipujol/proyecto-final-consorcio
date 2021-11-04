package com.proyectofinal.consorcio.controllers;

import com.proyectofinal.consorcio.services.EdificioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/edificios")
public class EdificioController {
    @Autowired
    private EdificioService edificioService;

    @PostMapping("/crearedificio")
    public String crear(@RequestParam String direccion){
        edificioService.crear(direccion);
        return "formAgregarDepartamento.html";
    }
}
