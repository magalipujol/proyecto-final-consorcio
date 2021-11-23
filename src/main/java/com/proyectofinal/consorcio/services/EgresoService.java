package com.proyectofinal.consorcio.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectofinal.consorcio.entities.Egreso;
import com.proyectofinal.consorcio.enums.TipoGasto;
import com.proyectofinal.consorcio.repositories.EgresoRepository;
import com.proyectofinal.consorcio.repositories.LiquidacionRepository;

@Service
public class EgresoService {	
	@Autowired
	private LiquidacionRepository liquidacionRepository;
	
	@Autowired
	private EgresoRepository egresoRepository;
	
	@Transactional
	public Egreso crear (String detalle, Double monto, String tipoGasto, String id_liquidacion) throws Exception{
		try {
			validar(detalle, monto, tipoGasto, id_liquidacion);

			Egreso egreso = new Egreso();

			egreso.setDetalle(detalle);
			egreso.setMonto(monto);
			egreso.setTipoGasto(TipoGasto.valueOf(tipoGasto));
			egreso.setAlta(true);
			egreso.setLiquidacion(liquidacionRepository.findById(id_liquidacion).get());
			egreso.setFecha(new Date());
			
			egresoRepository.save(egreso);
			return egreso;
		} catch (Exception e) {
			throw new Exception ("Error en crear egreso");
		}		
	}
	
	@Transactional
	public Egreso modificar (String id, String detalle, Double monto, String tipoGasto, String id_liquidacion) throws Exception {
		try {
			validar(detalle, monto, tipoGasto, id_liquidacion);
			
			Egreso egreso = egresoRepository.findById(id).get();
		
			egreso.setDetalle(detalle);
			egreso.setMonto(monto);
			egreso.setTipoGasto(TipoGasto.valueOf(tipoGasto));
			egreso.setFecha(new Date());
			
			egresoRepository.save(egreso);
			return egreso;
		} catch (Exception e) {
			throw new Exception ("Error en modificar egreso");
		}
	}
	
	@Transactional
	public void eliminar(String id) {
		try {			
			Egreso egreso = egresoRepository.findById(id).get();

			egreso.setAlta(false);
			egresoRepository.save(egreso);			
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Error al eliminar egreso");
		}
	}
	
	@Transactional(readOnly = true)
	public List<Egreso> listarActivosMes(String id_liquidacion) throws Exception {
		try {			
			return egresoRepository.buscarEgresos(id_liquidacion);
		} catch (Exception e) {
			throw new Exception("Error al listar egresos por tipo de gasto");
		}
	}
	
	@Transactional(readOnly = true)
	public List<Egreso> listarExtraordinariosMes(String id_liquidacion) throws Exception {
		try {			
			return egresoRepository.buscarExtraordinarios(id_liquidacion);
		} catch (Exception e) {
			throw new Exception("Error al listar egresos extraordinarios");
		}
	}
	
	@Transactional(readOnly = true)
	public List<Egreso> listarOrdinariosMes(String id_liquidacion) throws Exception {
		try {			
			return egresoRepository.buscarOrdinarios(id_liquidacion);
		} catch (Exception e) {
			throw new Exception("Error al listar egresos ordinarios");
		}
	}
	
	@Transactional
	public Egreso buscarPorId (String id) throws Exception {
		try {
			Egreso egreso = egresoRepository.findById(id).get();
			return egreso;
		} catch (Exception e) {
			throw new Exception ("Error al buscar egreso por id");
		}
	}
	
	public void validar(String detalle, Double monto, String tipoGasto, String id_liquidacion) throws Exception {
		try {
			if (detalle.equals(null) || detalle.isEmpty() || monto == null || 
					(!TipoGasto.ORDINARIO.toString().equals(tipoGasto) && !TipoGasto.EXTRAORDINARIO.toString().equals(tipoGasto)) || 
							id_liquidacion.equals(null) || id_liquidacion.isEmpty()) {
				throw new Exception("Este dato no puede ser nulo o estar vacío");
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
