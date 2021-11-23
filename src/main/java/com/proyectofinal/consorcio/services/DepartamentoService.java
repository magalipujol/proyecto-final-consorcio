package com.proyectofinal.consorcio.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectofinal.consorcio.entities.Departamento;
import com.proyectofinal.consorcio.entities.Edificio;
import com.proyectofinal.consorcio.entities.Liquidacion;
import com.proyectofinal.consorcio.entities.Usuario;
import com.proyectofinal.consorcio.repositories.DepartamentoRepository;
import com.proyectofinal.consorcio.repositories.EdificioRepository;
import com.proyectofinal.consorcio.repositories.LiquidacionRepository;

@Service
public class DepartamentoService {
	@Autowired
	private DepartamentoRepository departamentoRepository;
	@Autowired
	private EdificioRepository edificioRepository;
	@Autowired
	private LiquidacionRepository liquidacionRepository;

	@Transactional
	public void crear(Integer piso, String dpto, Double porcentaje, Long id_edificio, Usuario usuario) {
		try {
			validar(piso, dpto, porcentaje);
			
			Departamento departamento = new Departamento();

			departamento.setPiso(piso);
			departamento.setDpto(dpto);
			departamento.setPorcentajeParticipacion(porcentaje);			
			departamento.setEdificio(edificioRepository.findById(id_edificio).get());
			departamento.setUsuario(usuario);

			departamentoRepository.save(departamento);
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Error en crear departamento");
		}
	}

	@Transactional
	public void modificar(String id, Integer piso, String dpto, Double porcentaje) {
		try {
			validar(piso, dpto, porcentaje);

			Departamento departamento = departamentoRepository.findById(id).get();

			departamento.setPiso(piso);
			departamento.setDpto(dpto);
			departamento.setPorcentajeParticipacion(porcentaje);
			departamento.setAlta(true);
			
			departamentoRepository.save(departamento);
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Error en modificar departamento");
		}
	}

	@Transactional
	public void eliminar(String id) {
		try {
			Departamento departamento = departamentoRepository.findById(id).get();

			departamento.setAlta(false);			

			departamentoRepository.save(departamento);
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Error al eliminar departamento");
		}
	}
	
	@Transactional(readOnly = true)
	public List<Departamento> listarActivos(Long id_edificio) throws Exception {
		try {
			return departamentoRepository.buscarDptos(id_edificio);
		} catch (Exception e) {
			throw new Exception("Error al listar departamentos");
		}
	}
	
	public List<Double> totalesDepartamentos (String id_liquidacion) throws Exception {
		try {
			Liquidacion liquidacion = liquidacionRepository.findById(id_liquidacion).get();
			Edificio edificio = liquidacion.getEdificio();
			
			List<Departamento> listaDepartamentos = listarActivos(edificio.getId());
			List<Double> totalesDepartamentos = new ArrayList<Double>();
			
			for (Departamento dpto : listaDepartamentos) {
				Double total = liquidacion.getTotal() / 100 * dpto.getPorcentajeParticipacion();
				totalesDepartamentos.add(total);
			}
			
			return totalesDepartamentos;
		} catch (Exception e) {
			//throw new Exception ("Error en totalesDepartamento");
			throw new Exception ("No hay departamentos cargados para mostrar la liquidación");
		}		
	}
	
	public Departamento buscarPorId (String id) throws Exception {
		try {
			Departamento departamento = departamentoRepository.findById(id).get();
			return departamento;
		} catch (Exception e) {
			throw new Exception ("Error en buscarPorId departamento");
		}
	}
	
	public Departamento buscarPorUsuario (String id_usuario) throws Exception {
		try {
			Departamento departamento = departamentoRepository.buscarPorUsuario(id_usuario);
			return departamento;
		} catch (Exception e) {
			throw new Exception ("Error en buscar departamento por usuario");
		}
	}

	public void validar(Integer piso, String dpto, Double porcentaje) throws Exception {
		try {
			if (piso == null || dpto.equals(null) || porcentaje == null) {
				throw new Exception("Este dato no puede ser nulo");
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
