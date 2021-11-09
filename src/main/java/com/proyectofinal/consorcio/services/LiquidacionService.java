package com.proyectofinal.consorcio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectofinal.consorcio.entities.Liquidacion;
import com.proyectofinal.consorcio.enums.Meses;
import com.proyectofinal.consorcio.repositories.EdificioRepository;
import com.proyectofinal.consorcio.repositories.LiquidacionRepository;

@Service
public class LiquidacionService {
	
	@Autowired
	private EdificioRepository edificioRepository;
	
	@Autowired
	private LiquidacionRepository liquidacionRepository;
	
	
	
	@Transactional
	public Liquidacion crear (String mes, Integer anio, Long id_edificio) throws Exception{
		try {
	
			Liquidacion liquidacion = new Liquidacion();
			liquidacion.setMes(Meses.valueOf(mes));
			liquidacion.setAnio(anio);
			liquidacion.setEdificio(edificioRepository.findById(id_edificio).get());
		
			liquidacion.setAlta(true);
			liquidacion.setPublicar(false);
			
			liquidacionRepository.save(liquidacion);
			
			return liquidacion;
		} catch (Exception e) {
			throw new Exception ("Error en crearLiquidacion");
		}
		
		}
	
	@Transactional
	public Liquidacion modificar (String id, String mes, Integer anio) throws Exception{
		try {
	
			Liquidacion liquidacion = liquidacionRepository.findById(id).get();
			liquidacion.setMes(Meses.valueOf(mes));
			liquidacion.setAnio(anio);
				
						
			liquidacionRepository.save(liquidacion);
			
			return liquidacion;
		} catch (Exception e) {
			throw new Exception ("Error en modificarLiquidacion");
		}
		
		}
	
	@Transactional
	public void eliminar(String id) {
		try {
			
			Liquidacion liquidacion = liquidacionRepository.findById(id).get();

			liquidacion.setAlta(false);
			liquidacionRepository.save(liquidacion);
			
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Error al eliminar liquidación");
		}
	}
	
	@Transactional(readOnly = true)
	public List<Liquidacion> listarLiquidacionesAdmin() throws Exception {
		try {
			return liquidacionRepository.listarLiquidacionesAdmin();
		} catch (Exception e) {
			throw new Exception("Error al listar liquidaciones para el Administrador");
		}
	}
	
	@Transactional(readOnly = true)
	public List<Liquidacion> listarLiquidacionesUsuario() throws Exception {
		try {
			return liquidacionRepository.listarLiquidacionesUsuario();
		} catch (Exception e) {
			throw new Exception("Error al listar liquidaciones para el Usuario");
		}
	}
	
	@Transactional
	public Double total (String id) throws Exception{
		try {
			Liquidacion liquidacion = liquidacionRepository.findById(id).get();
			liquidacion.setTotal(liquidacionRepository.totalEgresos(id));
			return liquidacion.getTotal();
			
		} catch (Exception e) {
			throw new Exception("Error al calcular el total de la liquidación");
		}
	}
	
	@Transactional
	public Double totalOrdinarios (String id) throws Exception{
		try {
			Liquidacion liquidacion = liquidacionRepository.findById(id).get();
			liquidacion.setTotalOrdinarios(liquidacionRepository.totalOrdinarios(id));
			return liquidacion.getTotalOrdinarios();
			
		} catch (Exception e) {
			throw new Exception("Error al calcular el total de los gastos ordinarios");
		}
	}
	
	@Transactional
	public Double totalExtraordinarios (String id) throws Exception{
		try {
			Liquidacion liquidacion = liquidacionRepository.findById(id).get();
			liquidacion.setTotalExtraordinarios(liquidacionRepository.totalExtraordinarios(id));
			return liquidacion.getTotalExtraordinarios();
			
		} catch (Exception e) {
			throw new Exception("Error al calcular el total de los gastos extraordinarios");
		}
	}
	
	@Transactional
	public Liquidacion publicar (String id) throws Exception{
	try {
		Liquidacion liquidacion = liquidacionRepository.findById(id).get();
		liquidacion.setPublicar(true);
		liquidacionRepository.save(liquidacion);
		return liquidacion;
	} catch (Exception e) {
		throw new Exception ("Error al publicar liquidación");
	}	
		
	}
}
